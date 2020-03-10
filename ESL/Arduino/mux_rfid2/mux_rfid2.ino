#include <SPI.h>
#include <MFRC522.h>
#include <CD74HC4067.h>
#include "Wire.h"
#include <QueueList.h>

#define RST_PIN 9
#define SS 10

enum Protocol {
  HEADER,
  COMMAND,
  END
};

uint8_t command;
int resultAll[16];
uint8_t result;
uint8_t high;
uint8_t low;

unsigned int index;

CD74HC4067 my_mux(4, 5, 6, 7);  // create a new CD74HC4067 object with its four control pins

MFRC522 rfid(SS, RST_PIN);

class Locker{
  public:
    locker() {}
    uint8_t muxn;
    int serNum[5];
    int statuss;
    

    void check(){
        // Reset the loop if no new card present on the sensor/reader. This saves the entire process when idle.
  if ( ! rfid.PICC_IsNewCardPresent()) {
    statuss = 0;
    return;
  }

  // Select one of the cards
  if ( ! rfid.PICC_ReadCardSerial()) {
    statuss = 1;
    return;
  }

  // Dump debug info about the card; PICC_HaltA() is automatically called
  rfid.PICC_DumpToSerial(&(rfid.Uid));
  for (byte i = 0; i < &(rfid.Uid)->size; i++){
    if (&(rfid.Uid)->uidByte[i] != serNum[i]){
      statuss = 2;
      return;
    }
  }
  statuss = 3;
  return ;
    }

    void setSerNum(){
      for (byte i = 0; i < &(rfid.Uid)->size; i++){
        Sernum[i] = &(rfid.Uid)->uidByte[i];
      }
    }
}

Locker locker[16];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);    // Initialize serial communications with the PC
  while (!Serial);    // Do nothing if no serial port is opened (added for Arduinos based on ATMEGA32U4)
  SPI.begin();      // Init SPI bus
  rfid.PCD_Init();   // Init MFRC522
  delay(4);       // Optional delay. Some board do need more time after init to be ready, see Readme
  for(int i = 0; i < 16; i++){
    locker[i].setMuxn(i);
  }
  //rfid.PCD_DumpVersionToSerial();  // Show details of PCD - MFRC522 Card Reader details
  //Serial.println(F("Scan PICC to see UID, SAK, type, and data blocks..."));
  Wire.begin(0x71);
  Wire.onReceive(receiveEvent);
  Wire.onRequest(requestEvent);
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

void check(int n){
  my_mux.channel(n);
  locker[n].check();
  result = locker[n].statuss;
}

void checkAll(){
  for(int i = 0; i < 16; i++){
    my_mux.channel(i);
    locker[i].check();
    
  }
}

void loop() {
  // put your main code here, to run repeatedly:

}
