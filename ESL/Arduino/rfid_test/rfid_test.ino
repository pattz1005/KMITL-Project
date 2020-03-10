#include <SPI.h>
#include <MFRC522.h>
#include <CD74HC4067.h>

#define RST_PIN 9
#define SS 10


               // s0 s1 s2 s3
CD74HC4067 my_mux(4, 5, 6, 7);  // create a new CD74HC4067 object with its four control pins

MFRC522 rfid(SS, RST_PIN);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);    // Initialize serial communications with the PC
  while (!Serial);    // Do nothing if no serial port is opened (added for Arduinos based on ATMEGA32U4)
  SPI.begin();      // Init SPI bus
  rfid.PCD_Init();   // Init MFRC522
  delay(4);       // Optional delay. Some board do need more time after init to be ready, see Readme
  rfid.PCD_DumpVersionToSerial();  // Show details of PCD - MFRC522 Card Reader details
  Serial.println(F("Scan PICC to see UID, SAK, type, and data blocks..."));
  my_mux.channel(0);my_mux.channel(0);

}

void loop() {
  
  // put your main code here, to run repeatedly:
  if ( ! rfid.PICC_IsNewCardPresent()) {
    return;
  }

  // Select one of the cards
  if ( ! rfid.PICC_ReadCardSerial()) {
    return;
  }

  // Dump debug info about the card; PICC_HaltA() is automatically called
  rfid.PICC_DumpToSerial(&(rfid.uid));

}
