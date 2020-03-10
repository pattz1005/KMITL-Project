#include <CD74HC4067.h>


CD74HC4067 my_mux(4, 5, 6, 7);
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(12, OUTPUT);
  my_mux.channel(0);
  digitalWrite(12, HIGH);
}

void loop() {
  // put your main code here, to run repeatedly:
  
}
