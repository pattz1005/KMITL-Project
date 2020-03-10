#include <SPI.h>
#include <RFID.h>
#include <CD74HC4067.h>
#include "Wire.h"
#include <QueueList.h>

#define RST_PIN 9
#define SS_PIN 10


enum Protocol {
  HEADER,
  COMMAND,
  DATA,
  SIZE,
  CHECK,
  CODE,
  MODULE,
  RESET,
  END
};

QueueList <uint8_t> queue;
Protocol protocolUart;

uint8_t command;
uint8_t dataSize;
uint8_t inputByte;
int resultAll[16];
unsigned int result;
unsigned int index;
int code[5];


               // s0 s1 s2 s3
CD74HC4067 my_mux(4, 5, 6, 7);  // create a new CD74HC4067 object with its four control pins
RFID rfid(SS_PIN, RST_PIN); // Instance of the class

class Locker{
  public:
    locker() {}
    int serNum[5];
    int statuss;
    Locker() {}

    void setSerNum(int num[5]){
      for(int i = 0 ; i < 5; i++){
        serNum[i] = num[i]; 
      }
    }

    void check(){
      if(! rfid.isCard()){
        Serial.println("0");
        for(int i = 0; i<5;i++){
          serNum[i] = 0;
        }
        statuss = 0;
        return;
   }

      if(! rfid.readCardSerial()){
        Serial.println("1");
        for(int i = 0; i<5;i++){
          serNum[i] = 0;
        }
        statuss = 1;
        return;
   }
     /* if(rfid.serNum[0] != serNum[0]
         && rfid.serNum[1] != serNum[1]
         && rfid.serNum[2] != serNum[2]
         && rfid.serNum[3] != serNum[3]
         && rfid.serNum[4] != serNum[4]){
           Serial.println("2");
           statuss = 2;
           return;
         }*/
       Serial.println("2");
       statuss = 2;
       Serial.print("Hex: ");
       Serial.print(rfid.serNum[0],HEX);
       serNum[0] = rfid.serNum[0];
       Serial.print(", ");
       Serial.print(rfid.serNum[1],HEX);
       serNum[1] = rfid.serNum[1];
       Serial.print(", ");
       Serial.print(rfid.serNum[2],HEX);
       serNum[2] = rfid.serNum[2];
       Serial.print(", ");
       Serial.print(rfid.serNum[3],HEX);
       serNum[3] = rfid.serNum[4];
       Serial.print(", ");
       Serial.print(rfid.serNum[4],HEX);
       serNum[4] = rfid.serNum[4];
       Serial.println(" ");
       rfid.halt();    
    }


};



Locker locker[16];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.println("Serial begin");
  SPI.begin();
  Serial.println("SPI begin");
  rfid.init();
  Serial.println("RFID begin");
  //my_mux.channel(0);
  Wire.begin(0x73);
  Serial.println("Wire begin");
  Wire.onReceive(receiveEvent);
  Wire.onRequest(requestEvent);

  protocolUart = HEADER;
}


void check(int num){
  my_mux.channel(num);
  locker[num].check();
  result = locker[num].statuss;
}

void checkAll(){
  for(int i= 0; i <16; i++){
    my_mux.channel(i);
    locker[i].check();
    resultAll[i] = locker[i].statuss;
  }
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

void getProtocol(){
  //Serial.println("get protocol");
  if(queue.isEmpty()){
    //Serial.println("no queue");
    return;
  }

  switch(protocolUart){
    case HEADER :
      inputByte = queue.pop();
      Serial.println("HEADER");
      Serial.print("header = ");
      Serial.println(inputByte);
      if(inputByte == 0x02){
        protocolUart = COMMAND;
      }
      break;

     case COMMAND :
      inputByte = queue.pop();
      Serial.println("COMMAND");
      Serial.print("command = ");
      Serial.println(inputByte);
      if(inputByte == 0x21){
        command  = 0x21;
      }
      else if (inputByte == 0x22){
        command = 0x22;
      }

      else if (inputByte == 0x20){
        command = 0x20;
      }

      else if (inputByte == 0x023){
        command = 0x23;
      }
      else{
        protocolUart = HEADER;
      }
      protocolUart = SIZE;
      break;

     case SIZE : 
      Serial.println("SIZE");
      dataSize = queue.pop();
      Serial.print("size = ");
      Serial.println(dataSize); 
      Serial.print("command = ");
      Serial.println(command);

      if(command == 0x22){
        Serial.println("s22");
        if(dataSize == 0x01){
          protocolUart = CHECK;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else if (command == 0x21){
        Serial.println("s21");
        if(dataSize == 0x01){
          protocolUart == RESET;
        }
        else {
          protocolUart = HEADER;
        }
      }

      else if (command == 0x23){
        Serial.println("s23");
        if(dataSize == 0x01){
          protocolUart = CODE;
        }
        else {
          protocolUart = HEADER;
        }
      }

      else if (command ==0x20){
        Serial.println("s20");
        if(dataSize == 0x01){
          protocolUart = MODULE;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else{
        protocolUart = HEADER;
      }
      break;

     case CHECK :
      inputByte = queue.pop();
      if(inputByte >= 0x00 && inputByte < 0x10){
        index = inputByte; 
        Serial.println("CHECK");
        protocolUart = END;
      }
      else if (inputByte == 0xFF){
        command = 0xFF;
      }
      else {
        protocolUart = HEADER;
      }
      break;

      case CODE : 
        inputByte = queue.pop();
        if(inputByte >= 0x00 && inputByte < 0x10){
          Serial.println("CODE");
          index = inputByte;
          protocolUart = END;
        }
        else {
          protocolUart = HEADER;
        }
        break;

     case MODULE : 
      inputByte = queue.pop();
      if(inputByte == 0x00){
        Serial.println("MODULE");
        protocolUart = END;
      }
      else{
        protocolUart = HEADER;
      }
      break;

     case RESET :
      inputByte = queue.pop();
      if(inputByte == 0x00){
        protocolUart = END;
      }
      else {
        protocolUart = HEADER;
      }
      break;

     case END :
      inputByte = queue.pop();
      if(inputByte == 0x03){
        Serial.println("END");
        processProtocol();
      }
      protocolUart = HEADER;
      break;
  }
}

void processProtocol(){
  if(command == 0x22){
    if(index == 0xFF){
      checkAll();
    }
    else{
      check(index);
    }
  }

  else if (command == 0x20){
    
  }
  else if ( command == 0x21){
    Serial.println("processed reset");
    resetFunc();
  }
  else if (command == 0x23){
    readR(index);
  }
  
}
void readR(int n){
  my_mux.channel(n);
  check(n);
  for ( int i = 0; i <5; i++){
    code[i] = locker[n].serNum[i];
    Serial.print(code[i]);
    Serial.print(" ");
    Serial.println(locker[n].serNum[i]);
  }
}

void loop(){
  getProtocol();
    
}
 
void requestEvent() {
  if (command == 0x20){
    Wire.write(0x02);
    Wire.write(0x20);
    Wire.write(0x01);
    Wire.write(0x01);
    Wire.write(0x03);
  }
  if (command == 0x22){
    if(index == 0xFF){
      Wire.write(0x02);
      Wire.write(0x22);
      Wire.write(0x0F);
      for(int i = 0; i < 16; i++){
       Serial.print("locker no. ");
       Serial.print(i);
       Serial.print(" : ");
       Serial.println(resultAll[i]);
        Wire.write(resultAll[i]);
     }
    Wire.write(0x03);
    }
    else {
      Serial.print("locker no. ");
      Serial.print(index);
      Serial.print(" : ");
      Serial.println(result);
      Wire.write(0x02);
      Wire.write(0x22);
      Wire.write(0x01);
      Wire.write(result);
      Wire.write(0x03);
    }
    
  }

  else if ( command == 0x23){
    Wire.write(0x02);
    Wire.write(0x23);
    Wire.write(0x05);
    for(int i = 0; i <5 ; i++){
      Wire.write(code[i]);
    }
    Wire.write(0x03);
  }
}
