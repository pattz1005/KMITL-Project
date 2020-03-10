#include "Wire.h"

void setup() {
  // put your setup code here, to run once:
  Wire.begin();        // join i2c bus (address optional for master)
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:

 /* Wire.beginTransmission(0x71);
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x71, 5);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
  
  Wire.beginTransmission(0x71);
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x71, 9);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c, HEX);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000); */

  Wire.beginTransmission(0x72);
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x72, 6);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);
}
