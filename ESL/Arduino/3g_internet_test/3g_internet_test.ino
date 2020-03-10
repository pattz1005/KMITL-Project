#include "TEE_UC20.h"
#include "SoftwareSerial.h"
#include <AltSoftSerial.h>
#include "internet.h"
#include "tcp.h"

INTERNET net;
TCP tcp;

#define APN "internet"
#define USER ""
#define PASS ""

AltSoftSerial mySerial;

void debug(String data)
{
  Serial.println(data);
}

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  gsm.begin(&mySerial, 9600);
  gsm.Event_debug = debug;
  Serial.println(F("UC20"));
  gsm.PowerOn();
  while (gsm.WaitReady()) {}

  Serial.print(F("GetOperator --> "));
  Serial.println(gsm.GetOperator());
  Serial.print(F("SignalQuality --> "));
  Serial.println(gsm.SignalQuality());

  net.DisConnect();
  net.Configure(APN, USER, PASS);
  net.Connect();
  net.GetIP();
  
  Serial.println("check done");
  Serial.println(tcp.CheckConnection());
  

}

void loop() {
  // put your main code here, to run repeatedly:

}
