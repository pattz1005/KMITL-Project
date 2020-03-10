#include "Wire.h"

String inputString = "";         // a String to hold incoming data
bool stringComplete = false;  // whether the string is complete


void setup() {
  // put your setup code here, to run once:

  Wire.begin();
  Serial.begin(9600);
  inputString.reserve(10);
}

void checkLocker(){
  Wire.beginTransmission(0x73);
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x73, 15);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
}

void rfid(){
  Wire.beginTransmission(0x73);
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(0x01);
  Wire.write(0x01);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x73, 15);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
}
void openDoor(){
  Wire.beginTransmission(0x73);
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(0x01);
  Wire.write(0x02);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x73, 15);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
}

void openDoor2(){
  Wire.beginTransmission(0x73);
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(0x01);
  Wire.write(0x03);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x73, 15);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
}

void openDoor3(){
  Wire.beginTransmission(0x73);
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(0x01);
  Wire.write(0x04);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x73, 15);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
}
void loop() {

  if(stringComplete){
    Serial.println(inputString);
    if(inputString == "a\n"){
      checkLocker();
    }
    else if(inputString == "s\n"){
      rfid();
    }
    else if (inputString == "d\n"){
      openDoor();
    }
    else if (inputString == "f\n"){
      openDoor2();
    }
    else if (inputString == "g\n"){
      openDoor3();
    }
    inputString = "";
    stringComplete = false;
  }
 
  
  
}

void serialEvent(){
  while(Serial.available()){
    char inChar = (char)Serial.read();
    inputString += inChar;

    if(inChar == '\n'){
      stringComplete = true;
    }
  }
}
