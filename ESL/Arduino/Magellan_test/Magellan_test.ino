#include "Magellan.h"


const long interval = 10000;  //millisecond
unsigned long previousMillis = 0;

long cnt = 0;

Magellan magel;

//Token Key you can get from magellan platform
char auth[] = "b8f45160-c8a1-11e9-96dd-9fb5d8a71344";

String payload;




void setup() {
  Serial.begin(9600);
  //init Magellan LIB
  magel.begin(auth);
  // init dht

}

void loop() {
  delay(2000);


  cnt++;
  String countt = (String)cnt;


  //payload = "{\"Temperature\":" + Temperature + ",\"Humidity\":" + Humidity + ",\"Battery\":" + Battery + "}";
  payload = "{\"Count\":" + countt +"}";
  magel.post(payload);

}
