#include "AIS_NB_BC95.h"
#include "Wire.h"

String serverIP = "139.59.242.154"; // Your Server IP
String serverPort = "5005"; // Your Server Port

AIS_NB_BC95 AISnb;

String apnName = "RSU.NBIOT";
String nodename = "RSU01";
String udpData;


void setup() {
  // put your setup code here, to run once:

  AISnb.debug = true;
  
  Serial.begin(9600);
  Wire.begin();

  AISnb.setupDevice(serverPort);

  String ip1 = AISnb.getDeviceIP();
  delay(1000);

  pingRESP pingR = AISnb.pingIP(serverIP);

}

void sendUdp(String a){
  UDPSend udp = AISnb.sendUDPmsgStr(serverIP, serverPort, a);
}

void loop() {
  String test;
  Wire.beginTransmission(0x72);
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);

  
  Wire.requestFrom(0x72, 6);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    test += c;
    test += " ";
    Serial.print(" ");
  }
  Serial.println();

  delay(3000);

  //sendUdp(test);
  
    
  UDPReceive resp = AISnb.waitResponse();
  String b = resp.data;

  if (b != 0){
     Serial.println("data = " + b);
  }
  
  delay(3000);
}
