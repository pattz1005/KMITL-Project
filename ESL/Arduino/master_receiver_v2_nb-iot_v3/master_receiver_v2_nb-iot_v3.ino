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

  sendUdp("start");
}

void loop() {
  // put your main code here, to run repeatedly:
  UDPReceive resp = AISnb.waitResponse();
  String b = resp.data;

  if (b.length() != 0) {
    Serial.println(b);
    Serial.print("data = " );
    String a;
    a = hexToString(b);
    Serial.println(a);
    if ( b == "61") {
      Serial.println("locker");
      checkLocker();
    }
    else if (b == "62") {
      Serial.println("rfid");
      rfid();
    }
    else if (b == "63") {
      Serial.println("open");
      openDoor();
    }
  }
}

void sendUdp(String a) {
  UDPSend udp = AISnb.sendUDPmsgStr(serverIP, serverPort, a);
}

String hexToString(String hex)
{
  String text = "";
  for (int k = 0; k < hex.length(); k++)
  {
    if (k % 2 != 0)
    {
      char temp[3];
      sprintf(temp, "%c%c", hex[k - 1], hex[k]);
      int number = (int)strtol(temp, NULL, 16);
      text += char(number);
    }
  }
  return text;
}

void checkLocker() {
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
    udpData += c;
    udpData += " ";
    Serial.print(c);         // print the character
    Serial.print(" ");
  }
  sendUdp(udpData);
  udpData = "";
  Serial.println();

  delay(3000);
}

void rfid() {
  Wire.beginTransmission(0x73);
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);


  Wire.requestFrom(0x73, 5);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    udpData += c;
    udpData += " ";
    Serial.print(" ");
  }
  sendUdp(udpData);
  udpData = "";
  Serial.println();

  delay(3000);
}
void openDoor() {
  Wire.beginTransmission(0x74);
  Wire.write(0x02);
  Wire.write(0x22);
  Wire.write(0x01);
  Wire.write(0x00);
  Wire.write(0x03);
  Wire.endTransmission();

  delay(1000);


  Wire.requestFrom(0x74, 5);    // request 6 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    uint8_t c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
    udpData += c;
    udpData += " ";
    Serial.print("FACE ");
  }
  sendUdp(udpData);
  udpData = "";
  Serial.println();

  delay(3000);
}
