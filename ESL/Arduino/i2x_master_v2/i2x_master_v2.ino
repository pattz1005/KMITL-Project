#include <Wire.h>


String hello;
String scan;
String qr;
String wel;
uint8_t x;
uint8_t y;
uint8_t z;
uint8_t a;
uint8_t b;

void setup() {
  Serial.begin(9600);
  Wire.begin(); // join i2c bus (address optional for master)
  hello = "Hello World";
  scan = "Scan Me!!!aaaaaaaaaaaaaawsss";
  qr = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234";
  Serial.println(qr);
  wel = "Welcome";
  x = hello.length();
  y = scan.length();
  z = qr.length();
  Serial.println(z);
  a = wel.length();

}




void loop() {
  // stop transmitting

  

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(z);
  Serial.println(z);
  Wire.write("12345678901234567890123456789");
  Wire.endTransmission();
  delay(100);
  Wire.beginTransmission(0x71);
  Wire.write("0123456789012345678901234567890");
  Wire.endTransmission();
  delay(100);
  Wire.beginTransmission(0x71);
  Wire.write("123456789012345678901234567890");
  Wire.endTransmission();
  delay(100);
  Wire.beginTransmission(0x71);
  Wire.write("123456789012345678901234567890");;
  Wire.endTransmission();
  delay(100);
  Wire.beginTransmission(0x71);
  Wire.write("1234567890");
  Wire.endTransmission();
  delay(100);
  Wire.beginTransmission(0x71);
  Wire.write("1234");
  Wire.write(0x03);
  Wire.endTransmission();

  delay(8000);

}
