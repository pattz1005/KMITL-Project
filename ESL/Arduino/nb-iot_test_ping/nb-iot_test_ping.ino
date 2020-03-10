
#include "AIS_NB_BC95.h"

String serverIP = "139.59.242.154"; // Your Server IP
String serverPort = "5005"; // Your Server Port

AIS_NB_BC95 AISnb;

String apnName = "RSU.NBIOT";

String nodename = "NB-test";
String udpData;
long cnt = 0;

const long interval = 180000;  //millisecond
unsigned long previousMillis = 0;


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
    Serial.println(cnt);

    udpData = String( nodename + ","
                     + cnt
                    );

    UDPSend udp = AISnb.sendUDPmsgStr(serverIP, serverPort, udpData);
    Serial.println(udpData);
    //AISnb.pingIP(serverIP);
    previousMillis = currentMillis;
  }
  UDPReceive resp = AISnb.waitResponse();
}
