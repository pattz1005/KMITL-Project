#include <Wire.h>


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Wire.begin();
}

void loop() {
  // put your main code here, to run repeatedly:
  Wire.beginTransmission(0x72); // transmit to device #0x71
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(2000);

  Wire.requestFrom(0x72, 6);
  while(Wire.available()){
    int i = Wire.read();
    Serial.print(i);
    Serial.print(" ");
  }
  Serial.println();
  delay(1000);

}
