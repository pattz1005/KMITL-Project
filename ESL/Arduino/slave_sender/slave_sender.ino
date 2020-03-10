#include "Wire.h"

void setup() {
  // put your setup code here, to run once:
  Wire.begin(0x71);                // join i2c bus with address #8
  Wire.onRequest(requestEvent);
}

void loop() {
  // put your main code here, to run repeatedly:
  delay(1000);
}

void requestEvent() {
  Wire.write("hello "); // respond with message of 6 bytes
  // as expected by master
}
