#include <SPI.h>
#include <RFID.h>
#include <CD74HC4067.h>

#define RST_PIN 9
#define SS_PIN 10

unsigned int result;

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
        statuss = 0;
        return;
   }

      if(! rfid.readCardSerial()){
        Serial.println("1");
        statuss = 1;
        return;
   }
      /*if(rfid.serNum[0] != serNum[0]
         && rfid.serNum[1] != serNum[1]
         && rfid.serNum[2] != serNum[2]
         && rfid.serNum[3] != serNum[3]
         && rfid.serNum[4] != serNum[4]){
           Serial.println("2");
           statuss = 2;
           return;
         }*/
       Serial.println("3");
       statuss = 3;
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
       rfid.halt();    
    }

};



Locker locker[16];

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  SPI.begin();
  rfid.init();
  //SPI.setClockDivider(SPI_CLOCK_DIV128);
  //my_mux.channel(1);
  delay(100);
  
}


void check(int num){
  my_mux.channel(num);
  locker[num].check();
  result = locker[num].statuss;
  Serial.print("status : ");
  Serial.println(result);
}



void loop(){
// getProtocol();
    /*for(int i = 0 ; i< 2; i++){
      my_mux.channel(i);
      delay(100);
      Serial.print("locker : ");
      Serial.println(i);
      check(i);
      Serial.println("\n");
      delay(1000);
    }*/
    
    check(0);
    
    delay(1500);
}
 
