#include <SPI.h>
#include <RFID.h>
#include <CD74HC4067.h>

#define RST_PIN 9
#define SS_PIN 10

               // s0 s1 s2 s3
CD74HC4067 my_mux(4, 5, 6, 7);  // create a new CD74HC4067 object with its four control pins
RFID rfid(SS_PIN, RST_PIN); // Instance of the class


int muxn = 0;
bool isCard;

  int serNum0;
  int serNum1;
  int serNum2;
  int serNum3;
  int serNum4;



void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  SPI.begin();
  rfid.init();
  my_mux.channel(0);
}

void check(){
  //isCard = rfid.isCard();
   //Serial.println(isCard);
    if (rfid.isCard()) {
        if (rfid.readCardSerial()) {
            if (rfid.serNum[0] != serNum0
                && rfid.serNum[1] != serNum1
                && rfid.serNum[2] != serNum2
                && rfid.serNum[3] != serNum3
                && rfid.serNum[4] != serNum4
            ) {
                /* With a new cardnumber, show it. */
                Serial.println(" ");
                Serial.println("Card found");
                serNum0 = rfid.serNum[0];
                serNum1 = rfid.serNum[1];
                serNum2 = rfid.serNum[2];
                serNum3 = rfid.serNum[3];
                serNum4 = rfid.serNum[4];
               
                //Serial.println(" ");
                Serial.println("Cardnumber:");
                Serial.print("Dec: ");
    Serial.print(rfid.serNum[0],DEC);
                Serial.print(", ");
    Serial.print(rfid.serNum[1],DEC);
                Serial.print(", ");
    Serial.print(rfid.serNum[2],DEC);
                Serial.print(", ");
    Serial.print(rfid.serNum[3],DEC);
                Serial.print(", ");
    Serial.print(rfid.serNum[4],DEC);
                Serial.println(" ");
                        
                Serial.print("Hex: ");
    Serial.print(rfid.serNum[0],HEX);
                Serial.print(", ");
    Serial.print(rfid.serNum[1],HEX);
                Serial.print(", ");
    Serial.print(rfid.serNum[2],HEX);
                Serial.print(", ");
    Serial.print(rfid.serNum[3],HEX);
                Serial.print(", ");
    Serial.print(rfid.serNum[4],HEX);
                Serial.println(" ");
             } else {
               /* If we have the same ID, just write a dot. */
               Serial.print(".");
             }
          }
         
    }
    
    rfid.halt();
}

void loop(){
  check();
  delay(500);
    
}
 
  
