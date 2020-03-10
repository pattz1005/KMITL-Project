#include "Wire.h"
#include <QueueList.h>

#define P0  2
#define P1  3
#define P2  4
#define P3  5
#define P4  6
#define P5  7
#define P6  8
#define P7  9
#define P8  10
#define P9  11
#define P10  12
#define P11  13
#define P12  A0
#define P13  A1
#define P14  A2
#define P15  A3

enum Protocol {
  HEADER,
  COMMAND,
  RESET,
  STATUS,
  MODULE,
  CHECK,
  SIZE,
  END
};

uint8_t dataSize;
uint8_t command;
uint8_t inputByte;
uint8_t high;
uint8_t low;
uint8_t num;
unsigned int index;
uint8_t result;


QueueList <uint8_t> queue;
Protocol protocolUart;

class Locker {
  public:
    uint8_t pinNum;
    // 0 = close 1 = open
    bool Status;

    Locker() {}

    void check() {
      Status = digitalRead(pinNum);
    }
};

Locker locker[16];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
 /* pinMode(P0, INPUT_PULLUP);
  pinMode(P1, INPUT_PULLUP);
  pinMode(P2, INPUT_PULLUP);
  pinMode(P3, INPUT_PULLUP);
  pinMode(P4, INPUT_PULLUP);
  pinMode(P5, INPUT_PULLUP);
  pinMode(P6, INPUT_PULLUP);
  pinMode(P7, INPUT_PULLUP);
  pinMode(P8, INPUT_PULLUP);
  pinMode(P9, INPUT_PULLUP);
  pinMode(P10, INPUT_PULLUP);
  pinMode(P11, INPUT_PULLUP);
  pinMode(P12, INPUT_PULLUP);
  pinMode(P13, INPUT_PULLUP);
  pinMode(P14, INPUT_PULLUP);
  pinMode(P15, INPUT_PULLUP);*/

  pinMode(P0, INPUT);
  pinMode(P1, INPUT);
  pinMode(P2, INPUT);
  pinMode(P3, INPUT);
  pinMode(P4, INPUT);
  pinMode(P5, INPUT);
  pinMode(P6, INPUT);
  pinMode(P7, INPUT);
  pinMode(P8, INPUT);
  pinMode(P9, INPUT);
  pinMode(P10, INPUT);
  pinMode(P11, INPUT);
  pinMode(P12, INPUT);
  pinMode(P13, INPUT);
  pinMode(P14, INPUT);
  pinMode(P15, INPUT);
  protocolUart = HEADER;
  for ( int i = 0; i < 16; i++) {
    locker[i].pinNum = i + 2;
    locker[i].check();
    Serial.print("pin num is: ");
    Serial.println(locker[i].pinNum);
    Serial.print("status is: ");
    Serial.println(locker[i].Status);
  }
  index = 0;

  Wire.begin(0x72);
  Wire.onReceive(receiveEvent);
  Wire.onRequest(requestEvent);




}

void loop() {
  // put your main code here, to run repeatedly:
  getProtocol();
}


void receiveEvent(int howMany) {

  while (1 < Wire.available()) { // loop through all
    uint8_t c = Wire.read(); // receive byte as a character
    queue.push(c);
    // print the character
  }
  uint8_t x = Wire.read();
  queue.push(x);


}

void(* resetFunc) (void) = 0;

void getProtocol() {
  if (queue.isEmpty()) {
    return;
  }

  switch (protocolUart) {
    case HEADER :
      inputByte = queue.pop();
      Serial.println("HEADER");
      Serial.print("inputByte = ");
      Serial.println(inputByte);
      if (inputByte == 0x02) {
        protocolUart = COMMAND;
      }
      break;

    case COMMAND :
      inputByte = queue.pop();
      Serial.println("COMMAND");
      Serial.println(inputByte);
      if (inputByte == 0x21) {
        command = 0x21;
      }
      else if (inputByte == 0x22) {
        command = 0x22;
      }
      else if (inputByte == 0x20){
        command = 0x20;
      }
      else if (inputByte == 0x23){
        command = 0x23;
        Serial.print("command = ");
        Serial.println(command);
      }
      else {
        protocolUart = HEADER;
      }
      protocolUart = SIZE;
      break;

    case SIZE :
      Serial.println("SIZE");
      dataSize = queue.pop();
      Serial.print("size = ");
      Serial.println(dataSize);
      Serial.println(command);

      if (command == 0x21) {
        if (dataSize == 0x01) {
          protocolUart = RESET;
        }
        else {
          protocolUart = HEADER;
        }
      }

      else if (command == 0x20){
        if(dataSize == 0x01){
          protocolUart = MODULE;
        }
        else{
          protocolUart = HEADER;
        }
      }
      else if (command == 0x22) {
        if (dataSize == 0x01) {
          protocolUart = STATUS;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else if (command == 0x23){
        if(dataSize == 0x01){
          protocolUart = CHECK;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else{
        protocolUart = HEADER;
      }
      break;

    case RESET :
      inputByte = queue.pop();
      if (inputByte == 0x00) {
        Serial.println("RESET");
        protocolUart = END;
      } else {
        protocolUart = HEADER;
      }
      break;

    case STATUS :
      inputByte = queue.pop();
      if (inputByte == 0x00) {
        Serial.println("STATUS");
        protocolUart = END;
      } else {
        protocolUart = HEADER;
      }
      break;

     case MODULE :
      inputByte = queue.pop();
      if(inputByte == 0x00){
        Serial.println("MODULE");
        protocolUart = END;
      }else{
        protocolUart = HEADER;
      }
      break;

     case CHECK:
      inputByte = queue.pop();
      if(inputByte >= 0x00 && inputByte < 0x10){
        Serial.println("CHECK");
        num = inputByte;
        Serial.print("inputByte = ");
        Serial.println(inputByte);
        protocolUart = END;
      } else{
        protocolUart = HEADER;
      }
      break;
      

    case END :
      inputByte = queue.pop();
      Serial.println(inputByte);
      if (inputByte == 0x03) {
        Serial.println("END");
        processProtocol();
      }
      else{
        Serial.println("fail");
      }
      protocolUart = HEADER;
      break;
  }

}
void processProtocol() {
  if (command == 0x21) {
    Serial.println("process reset");
    resetFunc();
  }
  else if (command == 0x22) {
    Serial.println("process status");
    readStatus();
    checkStatus();
  }

  else if (command == 0x20){
  }
  else if (command == 0x23){
    Serial.println("process check");
    readS(num);
    checkS(num);
  }
}

void readStatus() {
  for (int i = 0; i < 16; i++) {
    locker[i].check();
  }
}

void checkStatus() {
  index = 0;
  Serial.println("checking status");
  for (int i = 15; i >= 0; i--){
    index = (index << 1);
    index += locker[i].Status;
    Serial.print("index = ");
    Serial.println(index, BIN);
  }

  Serial.print("index = ");
  Serial.println(index, BIN);
  low = index & 0xff;
  Serial.print("low = ");
  Serial.println(low, BIN);
  high = (index >> 8);
  Serial.print("high = ");
  Serial.println(high, BIN);
}

void readS(int n){
  Serial.println("readS");
  locker[n].check();
}

void checkS(int n){
  Serial.println("checkS");
  bool temp = locker[n].Status;
  Serial.print("temp = ");
  Serial.println(temp);
  if(temp == 1){
    result = 0x31;
  }
  else if (temp == 0){
    result = 0x30;
  }
  Serial.print("result = ");
  Serial.println(result);
}

void requestEvent() {
  if(command == 0x20){
    Wire.write(0x02);
    Wire.write(0x20);
    Wire.write(0x01);
    Wire.write(0x31);
    Wire.write(0x03);
  }
  else if(command == 0x22){
    Wire.write(0x02);
    Serial.print(0x02);
    Serial.print(" ");
    Wire.write(0x22);
    Serial.print(0x22);
    Serial.print(" ");
    Wire.write(0x02);
    Serial.print(0x02);
    Serial.print(" ");
    Wire.write(high);
    Serial.print(high);
    Serial.print(" ");
    Wire.write(low);
    Serial.print(low);
    Serial.print(" ");
    Wire.write(0x03);
    Serial.print(0x03);
    Serial.print(" ");
  }
  else if (command == 0x23){
    Wire.write(0x02);
    Serial.print(0x02);
    Serial.print(" ");
    Wire.write(0x23);
    Serial.print(0x23);
    Serial.print(" ");
    Wire.write(0x01);
    Serial.print(0x01);
    Serial.print(" ");
    Wire.write(result);
    Serial.print(result);
    Serial.print(" ");
    Wire.write(0x03);
    Serial.print(0x03);
    Serial.print(" ");
  }
  
}
