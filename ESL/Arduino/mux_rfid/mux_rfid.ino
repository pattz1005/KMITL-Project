#include <CD74HC4067.h>
#include <SPI.h>
#include <RFID.h>

#define SS_PIN 10
#define RST_PIN 9

               // s0 s1 s2 s3
CD74HC4067 my_mux(4, 5, 6, 7);
RFID rfid(SS_PIN, RST_PIN); 

class Locker{
  public:
    Locker() {}
    int serNum0;
    int serNum1;
    int serNum2;
    int serNum3;
    int serNum4;

    int cardStatus;

    int is_card(){
      if (rfid.isCard()) {
        if (rfid.readCardSerial()) {
            if (rfid.serNum[0] == serNum0
                && rfid.serNum[1] == serNum1
                && rfid.serNum[2] == serNum2
                && rfid.serNum[3] == serNum3
                && rfid.serNum[4] == serNum4
            ) {
               return 1;
             } else {
               return 2;
             }
          }
    }
    return 0;
    }

    void check(){
      cardStatus = is_card();
    }
    
};

Locker locker[16];
// Setup variables:

void setup()
{ 
  Serial.begin(9600);
  SPI.begin(); 
  rfid.init();
  
}

void loop()
{
    
}
