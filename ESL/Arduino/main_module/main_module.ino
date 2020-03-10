//==============================================
//ver 01.00
//+ GSM Communication
//+ connect door opener module
//==============================================

//==============================================
//Begin Define Zone
//==============================================
//general configuration
#define VER 1.0
#define I2C_RECV_BUFFER_SIZE 10
#define I2C_TRAN_BUFFER_SIZE 1
#define LCD_7SEGMENT_MODULE_ADDRESS 0x70
#define DOOR_CHECKER_MODULE_ADDRESS 0x72
#define DOOR_OPENER_MODULE_ADDRESS 0x74
#define RFID_READER_MODULE_ADDRESS 0x73

#define CHECK_STATUS_COMMAND 0x41
#define PUBLIC_CODE_COMMAND 0x42
#define OPEN_BOX_COMMAND 0x43
#define CHECK_BOX_STATUS_COMMAND 0x44
#define READ_RFID_COMMAND 0x45

//led status configuration
#define LED_STATUS_BLINK_INTERVAL 500 //millisecond
#define LED_STATUS_ERROR_BLINK_INTERVAL 250 //millisecond
#define LED_STATUS_PIN 13

// GSM Module
#define GSM_POLLING_INTERVAL 3000 //millisecond
#define APN "internet"
#define USER ""
#define PASS ""
#define GSM_RECV_BUFFER_SIZE 5
#define GSM_TRAN_BUFFER_SIZE 12
#define GSM_NET_TIME_DISCONNECT 3
//==============================================
//End Define Zone
//==============================================

//==============================================
//Begin Include Zone
//==============================================
//general library
#include <Arduino.h>
#include <avr/wdt.h>
#include <AltSoftSerial.h>
#include <Wire.h>
#include <QueueArray.h>
#include <avr/pgmspace.h>

// 7 segment Module
#include <Adafruit_GFX.h>
#include "Adafruit_LEDBackpack.h"

// GSM Module
#include "TEE_UC20.h"
#include "internet.h"
#include "http.h"

//JSON En/De
#include <ArduinoJson.h>
//==============================================
//End Include Zone
//==============================================

//==============================================
//Begin Variable Zone
//==============================================
//general variable
uint8_t lockerState = 1; //0: normal, 1:network disconnected
AltSoftSerial softSerial;; // RX: pin 8, TX: pin 9
unsigned long ledStatusBlinkTimestamp = 0;
QueueArray <uint8_t> i2cRxMessageBuffer;
enum i2cProtocalState {
  HEADER,
  COMMAND,
  SIZE,
  DATA,
  END
};
i2cProtocalState i2cState;
uint8_t i2cCommand;
uint8_t i2cDataSize;
uint8_t i2cDataSizeIndex;
uint8_t i2cRxBuffer[I2C_RECV_BUFFER_SIZE];
uint8_t i2cTxBuffer[I2C_TRAN_BUFFER_SIZE];

// 7segment Module
Adafruit_7segment matrix = Adafruit_7segment();

//GSM Module
StaticJsonDocument<200> doc; //buffer json object
INTERNET net;
HTTP http;
unsigned long gsmPullingTimestamp = 0;
uint8_t gsmRxBuffer[GSM_RECV_BUFFER_SIZE] = {0}; //buffer convert data from server
uint8_t gsmTxBuffer[GSM_TRAN_BUFFER_SIZE] = {0}; //buffer data send to serve
uint8_t gsmNetFailCount = 255; //count fail to connection and send data


//==============================================
//End Variable Zone
//==============================================

void setup() {
  //general setup
  softSerial.begin(9600); // for debug
  softSerial.println(F("==Init=="));
  pinMode(LED_STATUS_PIN, OUTPUT); //declare led status
  digitalWrite(LED_STATUS_PIN, HIGH);//assign value led status
  Wire.begin();

  // enable watchdog
  watchdogOn();

  //init gsm module
  initGSM();

  //setup finish print version
  softSerial.print(F(">Ready:"));
  softSerial.println(VER);
}

void loop() {
  ledStatusBlink(); // blink led

  connectGsmNet();
  wdt_reset(); // reset watch dog

  gsmRxMessage(); // polling data form server
  wdt_reset(); // reset watch dog

  processGsmMessage(); // process commmand from gsm buffer
  wdt_reset(); // reset watch dog

  I2cRxMessage(); // process commmand from i2c buffer
  wdt_reset(); // reset watch dog

  processI2CMessage();// send data to Server
  wdt_reset(); // reset watch dog

  gsmRxBuffer[0] = 0x00; //clear gsm buffer
}


////////////////////////////////////////////////////////////////////
////////////// function for initail                   //////////////
////////////////////////////////////////////////////////////////////
void initGSM() {
  softSerial.println(F("==Init GSM=="));
  gsm.begin(&Serial, 9600);
  gsm.PowerOff();
  wdt_reset();
  gsm.PowerOn();
  uint8_t countGsm = 0;
  while (gsm.WaitReady()) {
    if (countGsm++ < 30) {
      wdt_reset();
      softSerial.println(countGsm);
      wdt_reset();
      delay(1000);
    }
  }
  softSerial.print(F("Opt:"));
  softSerial.println(gsm.GetOperator());
  softSerial.print(F("SQ:"));
  softSerial.println((gsm.SignalQuality() * 2) - 113);
  net.DisConnect();
  net.Configure(APN, USER, PASS);
  softSerial.println(F("Connect"));
  connectGsmNet();
}

void init7segment() {
  softSerial.println(F("==Init 7Seg=="));
  matrix.begin(LCD_7SEGMENT_MODULE_ADDRESS);
  matrix.writeDigitNum(0, 0, false);
  matrix.writeDigitNum(1, 0, false);
  matrix.writeDigitNum(3, 0, false);
  matrix.writeDigitNum(4, 0, false);
  matrix.drawColon(false);
  matrix.writeDisplay();
}

////////////////////////////////////////////////////////////////////
////////////// General Function                  //////////////
////////////////////////////////////////////////////////////////////

void(* resetModule) (void) = 0;

void watchdogOn() {
  // Clear the reset flag, the WDRF bit (bit 3) of MCUSR.
  MCUSR = MCUSR & B11110111;

  // Set the WDCE bit (bit 4) and the WDE bit (bit 3)
  // of WDTCSR. The WDCE bit must be set in order to
  // change WDE or the watchdog prescalers. Setting the
  // WDCE bit will allow updtaes to the prescalers and
  // WDE for 4 clock cycles then it will be reset by
  // hardware.
  WDTCSR = WDTCSR | B00011000;

  // Set the watchdog timeout prescaler value to 1024 K
  // which will yeild a time-out interval of about 8.0 s.
  WDTCSR = B00100001;

  // Enable the watchdog timer interupt.
  WDTCSR = WDTCSR | B01000000;
  MCUSR = MCUSR & B11110111;
}

void ledStatusBlink() {
  if(lockerState != 0){
     if (millis() - ledStatusBlinkTimestamp > LED_STATUS_ERROR_BLINK_INTERVAL ) {
      digitalWrite(LED_STATUS_PIN, !digitalRead(LED_STATUS_PIN));
      ledStatusBlinkTimestamp = millis();
    }
  } else {
    if (millis() - ledStatusBlinkTimestamp > LED_STATUS_BLINK_INTERVAL ) {
      digitalWrite(LED_STATUS_PIN, !digitalRead(LED_STATUS_PIN));
      ledStatusBlinkTimestamp = millis();
    }
  }

}

void hexCharacterStringToBytes(byte *byteArray, char *hexString) {
  bool oddLength = strlen(hexString) & 1;
  byte currentByte = 0;
  byte byteIndex = 0;
  for (byte charIndex = 0; charIndex < strlen(hexString); charIndex++) {
    bool oddCharIndex = charIndex & 1;
    if (oddLength) { // If the length is odd
      if (oddCharIndex) { // odd characters go in high nibble
        currentByte = nibble(hexString[charIndex]) << 4;
      } else { // Even characters go into low nibble
        currentByte |= nibble(hexString[charIndex]);
        byteArray[byteIndex++] = currentByte;
        currentByte = 0;
      }
    } else { // If the length is even
      if (!oddCharIndex) { // Odd characters go into the high nibble
        currentByte = nibble(hexString[charIndex]) << 4;
      } else { // Odd characters go into low nibble
        currentByte |= nibble(hexString[charIndex]);
        byteArray[byteIndex++] = currentByte;
        currentByte = 0;
      }
    }
  }
}

byte nibble(char c) {
  if (c >= '0' && c <= '9') return c - '0';
  if (c >= 'a' && c <= 'f') return c - 'a' + 10;
  if (c >= 'A' && c <= 'F') return c - 'A' + 10;
  return 0;  // Not a valid hexadecimal character
}

String arrayByteToString(uint8_t byteArray[], uint8_t len) {
  char buffer[(len * 2) + 1];
  for (unsigned int i = 0; i < len; i++) {
    uint8_t nib1 = (byteArray[i] >> 4) & 0x0F;
    uint8_t nib2 = (byteArray[i] >> 0) & 0x0F;
    buffer[i * 2 + 0] = nib1  < 0xA ? '0' + nib1  : 'A' + nib1  - 0xA;
    buffer[i * 2 + 1] = nib2  < 0xA ? '0' + nib2  : 'A' + nib2  - 0xA;
  }
  buffer[len * 2] = '\0';
  String str((char*)buffer);
  return str;
}

String byteToString(uint8_t byteData) {
  char buffer[3];
  byte nib1 = (byteData >> 4) & 0x0F;
  byte nib2 = (byteData >> 0) & 0x0F;
  buffer[0] = nib1  < 0xA ? '0' + nib1  : 'A' + nib1  - 0xA;
  buffer[1] = nib2  < 0xA ? '0' + nib2  : 'A' + nib2  - 0xA;
  buffer[2] = '\0';
  String str((char*)buffer);
  return str;
}

////////////////////////////////////////////////////////////////////
////////////// function for GSM Communication         //////////////
////////////////////////////////////////////////////////////////////
void connectGsmNet() {
  if (gsmNetFailCount > GSM_NET_TIME_DISCONNECT) {
    lockerState = 1;
    uint8_t countOut = 0;
    while (!net.Connect()) {
      softSerial.println(F("ReConnect"));
      if (countOut++ < 5) {
        wdt_reset();
      }
    }
    gsmNetFailCount = 0;
    http.begin(1);
  } else {
    lockerState = 0;
  }
}

void gsmRxMessage() {
  if (millis() - gsmPullingTimestamp < GSM_POLLING_INTERVAL ) {
    return;
  }
  gsmPullingTimestamp = millis();
  softSerial.println(F("==Polling=="));
  String postData = String(F("{\"DevEUI\":\""))
                    + String(F("1296D03181301691"))
                    + String(F("\",\"Key\":\""))
                    + String(F("14125880972394860306538024"))
                    + String(F("\"}"));
  softSerial.println(postData);

  wdt_reset(); // reset watch dog
  if (!http.url(F("http://www.teksol-enterprise.com/passlockerhub/services/public/api/connect/uplink/polling"))) {
    softSerial.println(F("Fail"));
    wdt_reset(); // reset watch dog
    gsmNetFailCount++;
    return;
  }
  softSerial.println(F("Send"));
  wdt_reset(); // reset watch dog
  int statusCode = http.post(postData);
  if (statusCode == 200) {
    wdt_reset();
    gsmNetFailCount = 0;
    softSerial.println(F("Success"));
    String resp = http.ReadData(100);
    DeserializationError error = deserializeJson(doc, resp);
    if (error) {
      softSerial.print(F("Err:"));
      softSerial.println(error.c_str());
      return;
    }

    //    const char* keyResp = doc["Key"];
    //    softSerial.println(keyResp);
    //compare string
    //    if(se){ key differance
    //      return;
    //    }

    JsonArray arr = doc["ArrPayloadHex"].as<JsonArray>();
    for (JsonVariant value : arr) {
      hexCharacterStringToBytes(gsmRxBuffer, value.as<char*>());
      //        softSerial.println(value.as<char*>());
    }
  } else {
    wdt_reset();
    if(statusCode == -1){
      gsmNetFailCount++;
    }
    gsmRxBuffer[0] == 0x00;
    softSerial.print(F("Error:"));
    softSerial.println(statusCode);
  }
}

void processGsmMessage() {
  if (gsmRxBuffer[0] == 0x00) {
    return;
  }
  if (gsmRxBuffer[0] == CHECK_STATUS_COMMAND) { // check locker status
    softSerial.println(F(">ReqStatus"));
  } else if (gsmRxBuffer[0] == PUBLIC_CODE_COMMAND) { // set public code locker
    setPublicCode(gsmRxBuffer[1], gsmRxBuffer[2], gsmRxBuffer[3], gsmRxBuffer[4]);
  } else if (gsmRxBuffer[0] == OPEN_BOX_COMMAND) { // open locker box
    openLocker(gsmRxBuffer[1], gsmRxBuffer[2]);
  } else if (gsmRxBuffer[0] == CHECK_BOX_STATUS_COMMAND) { // check locker box status
    checkLockerDoor(gsmRxBuffer[1], gsmRxBuffer[2]);
  } else if (gsmRxBuffer[0] == READ_RFID_COMMAND) { // reader rfid locker box
    readRfid(gsmRxBuffer[1], gsmRxBuffer[2]);
  }
}


void gsmTxMessage(uint8_t command, uint8_t dataSize) {
  softSerial.println(F("==Transfer=="));
  wdt_reset(); // reset watch dog
  // get ready for data
  String postData = String(F("{\"DevEUI\":\""))
                    + String(F("1296D03181301691"))
                    + String(F("\",\"RSSI\":\""))
                    + ((gsm.SignalQuality() * 2) - 113)
                    + String(F("\",\"IP\":\""))
                    + net.GetIP()
                    + String(F("\",\"Payload\":\""))
                    + byteToString(command)
                    + arrayByteToString(gsmTxBuffer, dataSize)
                    + String(F("\"}"));
  softSerial.println(postData);
  wdt_reset(); // reset watch dog
  if (!http.url(F("http://www.teksol-enterprise.com/passlockerhub/services/public/api/connect/uplink/transfer"))) {
    softSerial.println(F("Fail"));
    wdt_reset(); // reset watch dog
    gsmNetFailCount++;
    return;
  }
  softSerial.println(F("Send"));
  wdt_reset(); // reset watch dog
  int statusCode = http.post(postData);
  if (statusCode == 200) {
    softSerial.println(F("Success"));
    gsmNetFailCount = 0;
  } else {
    if(statusCode == -1){
      gsmNetFailCount++;
    }
    softSerial.print(F("Error:"));
    softSerial.println(statusCode);
  }
}

////////////////////////////////////////////////////////////////////
// function i2c                                       //////////////
////////////////////////////////////////////////////////////////////
void I2cTxRxMessage(uint8_t address, uint8_t command, uint8_t dataSize, uint8_t dataSizeRead) {
  Wire.beginTransmission(address);
  Wire.write(0x02);
  Wire.write(command);
  Wire.write(dataSize);
  for (uint8_t indexCount = 0; indexCount < dataSize; indexCount++) {
    Wire.write(i2cTxBuffer[indexCount]);
  }
  Wire.write(0x03);
  Wire.endTransmission();
  if (dataSizeRead) {
    delay(800);
    Wire.requestFrom(address, dataSizeRead);
    while (Wire.available()) {
      i2cRxMessageBuffer.push(Wire.read());
      delay(1);
    }
  }
}

void I2cRxMessage() {
  while (!i2cRxMessageBuffer.isEmpty()) {
    uint8_t messageByte = i2cRxMessageBuffer.pop();
    switch (i2cState) {
      case HEADER :
        if (messageByte == 0x02) {
          i2cState = COMMAND;
        }
        break;
      case COMMAND :
        i2cCommand = messageByte;
        i2cState = SIZE;
        break;
      case SIZE :
        i2cDataSize = messageByte;
        i2cDataSizeIndex = 0;
        i2cState = DATA;
        break;
      case DATA :
        i2cRxBuffer[i2cDataSizeIndex] = messageByte;
        i2cDataSizeIndex++;
        if (i2cDataSizeIndex >= i2cDataSize) {
          i2cState = END;
        }
        break;
      case END :
        if (messageByte == 0x03) {
        }
        i2cState = HEADER;
        break;
      default:
        break;
    }
  }
}

void processI2CMessage() {
  if (gsmRxBuffer[0] == 0x00) return;
  if (gsmRxBuffer[0] == CHECK_STATUS_COMMAND) { // check locker status
    //status normal;
    gsmTxBuffer[0] = 0x31;
    gsmTxBuffer[1] = 0x30;
    gsmTxBuffer[2] = 0x30;
    gsmTxMessage(CHECK_STATUS_COMMAND, 3);
    return;
  } else if (gsmRxBuffer[0] == PUBLIC_CODE_COMMAND) { // set public code locker
    // send back public code
    gsmTxBuffer[0] = gsmRxBuffer[1];
    gsmTxBuffer[1] = gsmRxBuffer[2];
    gsmTxBuffer[2] = gsmRxBuffer[3];
    gsmTxBuffer[3] = gsmRxBuffer[4];
    gsmTxMessage(PUBLIC_CODE_COMMAND, 4);
    return;
  } else if (gsmRxBuffer[0] == OPEN_BOX_COMMAND) { // open locker box
    // send locker box number
    gsmTxBuffer[0] = gsmRxBuffer[1];
    gsmTxBuffer[1] = gsmRxBuffer[2];
    //open locker box
    gsmTxBuffer[2] = i2cRxBuffer[0]; // status of door
    gsmTxMessage(OPEN_BOX_COMMAND, 3);
    return;
  } else if (gsmRxBuffer[0] == CHECK_BOX_STATUS_COMMAND) { // check locker box status
    // send locker box number
    gsmTxBuffer[0] = gsmRxBuffer[1];
    gsmTxBuffer[1] = gsmRxBuffer[2];
    //status from locker box number
    gsmTxBuffer[2] = i2cRxBuffer[0]; // status of door
    gsmTxMessage(CHECK_BOX_STATUS_COMMAND, 3);
    return;
  } else if (gsmRxBuffer[0] == READ_RFID_COMMAND) { // reader rfid locker box
    // send locker box number
    gsmTxBuffer[0] = gsmRxBuffer[1];
    gsmTxBuffer[1] = gsmRxBuffer[2];
    //RFID from reader
    gsmTxBuffer[2] = i2cRxBuffer[0];
    gsmTxBuffer[3] = i2cRxBuffer[1];
    gsmTxBuffer[4] = i2cRxBuffer[2];
    gsmTxBuffer[5] = i2cRxBuffer[3];
    gsmTxBuffer[6] = i2cRxBuffer[4];
    gsmTxBuffer[7] = i2cRxBuffer[5];
    gsmTxBuffer[8] = i2cRxBuffer[6];
    gsmTxBuffer[9] = i2cRxBuffer[7];
    gsmTxBuffer[10] = i2cRxBuffer[8];
    gsmTxBuffer[11] = i2cRxBuffer[9];
    gsmTxMessage(READ_RFID_COMMAND, 12);
    return;
  }
}

////////////////////////////////////////////////////////////////////
// function for locker module                         //////////////
////////////////////////////////////////////////////////////////////
void setPublicCode(uint8_t code1, uint8_t code2, uint8_t code3, uint8_t code4) {
  softSerial.print(">PublicCode: ");
  softSerial.write(code1);
  softSerial.write(code2);
  softSerial.write(code3);
  softSerial.write(code4);
  softSerial.println("");
  matrix.writeDigitNum(0, code1 - 48, false);
  matrix.writeDigitNum(1, code2 - 48, false);
  matrix.writeDigitNum(3, code3 - 48, false);
  matrix.writeDigitNum(4, code4 - 48, false);
  matrix.writeDisplay();
}

void openLocker(uint8_t boxNumber1, uint8_t boxNumber2) {
  uint8_t boxNumber = ((boxNumber1 - 48) * 10) + (boxNumber2 - 48);
  softSerial.print(F(">OpenBox:"));
  softSerial.println(boxNumber, DEC);
  i2cTxBuffer[0] = boxNumber - 1;
  i2cRxBuffer[0] = 0x30;
  I2cTxRxMessage(DOOR_OPENER_MODULE_ADDRESS, 0x22, 0x01, 5);
}

void checkLockerDoor(uint8_t boxNumber1, uint8_t boxNumber2) {
  uint8_t boxNumber = ((boxNumber1 - 48) * 10) + (boxNumber2 - 48);
  softSerial.print(F(">CheckBox:"));
  softSerial.println(boxNumber, DEC);
  //i2cTxBuffer[0] = 0; //checl all
  i2cTxBuffer[0] = boxNumber - 1;
  i2cRxBuffer[0] = 0x30;
  I2cTxRxMessage(DOOR_CHECKER_MODULE_ADDRESS, 0x23, 0x01, 5);
}

void readRfid(uint8_t boxNumber1, uint8_t boxNumber2) {
  uint8_t boxNumber = ((boxNumber1 - 48) * 10) + (boxNumber2 - 48);
  softSerial.print(F(">ReadRfid:"));
  softSerial.println(boxNumber, DEC);
  i2cTxBuffer[0] = boxNumber - 1;
  i2cRxBuffer[0] = 0x30;
  i2cRxBuffer[1] = 0x30;
  i2cRxBuffer[2] = 0x30;
  i2cRxBuffer[3] = 0x30;
  i2cRxBuffer[4] = 0x30;
  i2cRxBuffer[5] = 0x30;
  i2cRxBuffer[6] = 0x30;
  i2cRxBuffer[7] = 0x30;
  i2cRxBuffer[8] = 0x30;
  i2cRxBuffer[9] = 0x30;
  I2cTxRxMessage(RFID_READER_MODULE_ADDRESS, 0x23, 0x01, 14); // send read code
}
