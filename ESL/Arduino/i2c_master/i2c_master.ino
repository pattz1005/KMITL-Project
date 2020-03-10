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
  scan = "Scan Me!!!";
  qr = "https://www.teksol-enterprise.com/smartlocker/view/#/locker/box/list/6804";
  wel = "Welcome";
  x = hello.length();
  y = scan.length();
  z = qr.length();
  a = wel.length();

}




void loop() {
  // stop transmitting

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x01);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x25);
  Wire.write(x);
  Serial.println(x);
  Wire.write("Hello World");
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);


  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x25);
  Wire.write(y);
  Serial.println(y);
  Wire.write(scan.c_str());
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x23);
  Wire.write(z);
  Serial.println(z);
  Wire.write("https://www.teksol-enterprise");
  Wire.endTransmission();
  Wire.beginTransmission(0x71);
  Wire.write(".com/smartlocker/view/#/locker");
  Wire.endTransmission();
  Wire.beginTransmission(0x71);
  Wire.write("/box/list/8231");
  Wire.write(0x03);
  Wire.endTransmission();

  delay(7000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x25);
  Wire.write(a);
  Serial.println(a);
  Wire.write(wel.c_str());
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x24);
  Wire.write(y);
  Serial.println(y);
  Wire.write(scan.c_str());
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);


  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x01);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  Wire.beginTransmission(0x71); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x00);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);




  /*๕ Wire.beginTransmission(0x71); // transmit to device #0x71
    Wire.write(0x02);       // sends five bytes
    Wire.write(0x21);
    Wire.write(0x01);
    Wire.write(0x00);
    Wire.write(0x03);
    Wire.endTransmission();    // stop transmitting

    delay(500);
  */
}
