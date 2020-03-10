#include <SPI.h>
#include <RFID.h>
#include <CD74HC4067.h>

#define RST_PIN 9
#define SS1 10
#define SS2 15

RFID rfid1(SS1, RST_PIN);


int serNum0;
int serNum1;
int serNum2;
int serNum3;
int serNum4;

CD74HC4067 my_mux(4, 5, 6, 7);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  SPI.begin();
  rfid1.init();
  
  my_mux.channel(1);

}

void loop() {
  // put your main code here, to run repeatedly:
    if(rfid1.isCard()){
      if(rfid1.readCardSerial()){
        if (rfid1.serNum[0] != serNum0
                && rfid1.serNum[1] != serNum1
                && rfid1.serNum[2] != serNum2
                && rfid1.serNum[3] != serNum3
                && rfid1.serNum[4] != serNum4
            ) {
              Serial.println("\nCard found");
              serNum0 = rfid1.serNum[0];
              serNum1 = rfid1.serNum[1];
              serNum2 = rfid1.serNum[2];
              serNum3 = rfid1.serNum[3];
              serNum4 = rfid1.serNum[4];

             Serial.println("Cardnumber:");
             Serial.print("Dec: ");
             Serial.print(rfid1.serNum[0],DEC);
             Serial.print(", ");
             Serial.print(rfid1.serNum[1],DEC);
             Serial.print(", ");
             Serial.print(rfid1.serNum[2],DEC);
             Serial.print(", ");
             Serial.print(rfid1.serNum[3],DEC);
             Serial.print(", ");
             Serial.print(rfid1.serNum[4],DEC);
             Serial.println(" ");
                        
             Serial.print("Hex: ");
             Serial.print(rfid1.serNum[0],HEX);
             Serial.print(", ");
             Serial.print(rfid1.serNum[1],HEX);
             Serial.print(", ");
             Serial.print(rfid1.serNum[2],HEX);
             Serial.print(", ");
             Serial.print(rfid1.serNum[3],HEX);
             Serial.print(", ");
             Serial.print(rfid1.serNum[4],HEX);
             Serial.println(" ");
             }
             else{
              Serial.print(".");
             }
            }
             
      }

     

      rfid1.halt();
     
    }
   
  
