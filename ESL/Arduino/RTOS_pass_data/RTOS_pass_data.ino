#include <Arduino_FreeRTOS.h>
void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);

}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.print("this is run on core: ");
  Serial.println(xPortGetCoreID());

  delay(1000);

}
