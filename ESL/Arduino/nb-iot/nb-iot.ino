#include "AIS_NB_BC95.h"

String apnName = "devkit.nb";

String serverIP = "139.59.242.154"; // Your Server IP
String serverPort = "5005"; // Your Server Port

AIS_NB_BC95 AISnb;

const long interval = 10000;  //millisecond
unsigned long previousMillis = 0;

long cnt = 0;
void setup() {
  // put your setup code here, to run once:

  AISnb.debug = true;

  Serial.begin(9600);

  AISnb.setupDevice(serverPort);

  String ip1 = AISnb.getDeviceIP();
  delay(1000);

  pingRESP pingR = AISnb.pingIP(serverIP);
  previousMillis = millis();

}

void loop() {
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval)
  {
    // put your main code here, to run repeatedly:
    cnt++;
    String dataSend = "{\"id\":\"NB001\",\"data\":" + String(cnt) + "}";
    UDPSend udp = AISnb.sendUDPmsgStr(serverIP, serverPort, dataSend);
    previousMillis = currentMillis;
  }
  UDPReceive resp = AISnb.waitResponse();
}
