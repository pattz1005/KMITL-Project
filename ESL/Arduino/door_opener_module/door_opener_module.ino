//==============================================
//ver 00.01
//+ control mux
//+ control open door
//+ i2c communication
//==============================================

//==============================================
//Begin Define Zone
//==============================================
  //general configuration
  #define VER 1.30
  #define I2C_ADDRESS 0x74
  #define I2C_RECV_BUFFER_SIZE 5
  #define I2C_TRAN_BUFFER_SIZE 5
  
  //led status configuration
  #define LED_STATUS_BLINK_INTERVAL 500 //millisecond
  #define LED_STATUS_PIN 13
  
  //relay mux Module configuration
  #define MUX_S1_PIN 2
  #define MUX_S2_PIN 3
  #define MUX_S3_PIN 4
  #define MUX_S4_PIN 5
  #define MUX_SIG_PIN 6 
//==============================================
//End Define Zone
//==============================================

//==============================================
//Begin Include Zone
//==============================================
  //general library
  #include <Arduino.h>
  #include <Wire.h>
  #include <AltSoftSerial.h>
  #include <avr/wdt.h>
  #include <QueueArray.h>
  
  //mux module library
  #include <CD74HC4067.h> 
//==============================================
//End Include Zone
//==============================================

//==============================================
//Begin Variable Zone
//==============================================
  //general variable
  AltSoftSerial softSerial; // RX: pin 8, TX: pin 9
  unsigned long ledStatusBlinkTimestamp = 0;
  QueueArray <uint8_t> i2cMessageBuffer;
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
  uint8_t i2cDataReceiveBuffer[I2C_RECV_BUFFER_SIZE];
  uint8_t i2cDataTransmitBuffer[I2C_TRAN_BUFFER_SIZE];
  
  //relay mux Module
  CD74HC4067 doorMux(MUX_S1_PIN, MUX_S2_PIN, MUX_S3_PIN, MUX_S4_PIN);  // create a new CD74HC4067 object with its four control pins
//==============================================
//End Variable Zone
//==============================================

void setup() {
  //general setup
  softSerial.begin(9600); // for debug
  Wire.begin(I2C_ADDRESS); // start i2c
  Wire.onReceive(receiveEvent);
  Wire.onRequest(requestEvent);
  pinMode(LED_STATUS_PIN, OUTPUT); //declare led status
  digitalWrite(LED_STATUS_PIN, HIGH);//assign value led status
  
  //relay mux Module
  pinMode(MUX_SIG_PIN, OUTPUT);
  digitalWrite(MUX_SIG_PIN, LOW);

  // enable watchdog
  wdt_enable(WDTO_2S);
  
  //setup finish print version
  softSerial.print(F("Ver:"));
  softSerial.println(VER);
}

void loop() {
  wdt_reset(); // reset watch dog
  ledStatusBlink(); // blink led
  getI2cMessage(); // read i2c message from buffer and exe
}

void(* resetModule) (void) = 0;

void receiveEvent(){
  while (Wire.available()) {
    i2cMessageBuffer.push(Wire.read());
  }
}

void requestEvent(){
  if(i2cCommand == 0x20){
    Wire.write(0x02);
    Wire.write(0x20);
    Wire.write(0x01);
    Wire.write(i2cDataTransmitBuffer[0]);
    Wire.write(0x03);
  } else if (i2cCommand == 0x22){
    Wire.write(0x02);
    Wire.write(0x22);
    Wire.write(0x01);
    Wire.write(i2cDataTransmitBuffer[0]);
    Wire.write(0x03);
  }
}

void getI2cMessage(){
  if(i2cMessageBuffer.isEmpty()) return;
  uint8_t messageByte = i2cMessageBuffer.pop();
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
      i2cDataReceiveBuffer[i2cDataSizeIndex] = messageByte;
      i2cDataSizeIndex++;
      if(i2cDataSizeIndex >= i2cDataSize){
        i2cState = END;
      }
      break;
    case END :
      if (messageByte == 0x03) {
        processProtocol();
      }
      i2cState = HEADER;
      break;
    default:
      break;
  }
}

void processProtocol(){
  // check command
  if(i2cCommand == 0x20){// Check Status Module
    i2cDataTransmitBuffer[0] = 0x01;
  } else if(i2cCommand == 0x21) { // Reset Module
    resetModule();
  } else if(i2cCommand == 0x22) { // Open door
    i2cDataTransmitBuffer[0] = 0x00;
    openLocker(i2cDataReceiveBuffer[0]);
  }
}

void ledStatusBlink(){
  if(millis() - ledStatusBlinkTimestamp > LED_STATUS_BLINK_INTERVAL ){
    digitalWrite(LED_STATUS_PIN, !digitalRead(LED_STATUS_PIN));
    ledStatusBlinkTimestamp = millis();
  }
}

void openLocker(uint8_t doorNumber){
  softSerial.print(F("open locker: "));
  softSerial.println(doorNumber+1, DEC);
  doorMux.channel(doorNumber);
  digitalWrite(MUX_SIG_PIN, HIGH);
  delay(300);
  digitalWrite(MUX_SIG_PIN, LOW);
  i2cDataTransmitBuffer[0] = 0x01;
}
