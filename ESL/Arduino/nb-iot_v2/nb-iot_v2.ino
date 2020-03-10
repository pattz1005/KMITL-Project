#include "AIS_NB_BC95.h"

String serverIP = "139.59.242.154"; // Your Server IP
String serverPort = "5005"; // Your Server Port

AIS_NB_BC95 AISnb;

String apnName = "RSU.NBIOT";
String nodename = "RSU01";
String udpData;

const long interval = 18000;  //millisecond
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
/*
void convert(String a){
  int str_len = a.length() + 1;
  char char_array[str_len];
  a.toCharArray(char_array, str_len);
  hexToString(char_array);
  
}

void hexToString(char hex[]){
  char temp[3];
  char c;
  int index;
  int i;
  for (i = 0; i < sizeof(hex); i += 2) {
    temp[0] = hex[i];
    temp[1] = hex[i + 1];
    temp[2] = '\0';
    index = atoi(temp);
    c = toascii(index);
    Serial.print(c);
  }
}*/

String hexToString(String hex)
{
    String text = "";
     
    for(int k=0;k< hex.length();k++)
    {
      if(k%2!=0)
      {
        char temp[3];
        sprintf(temp,"%c%c",hex[k-1],hex[k]);
        int number = (int)strtol(temp, NULL, 16);
        text+=char(number);
        
      }
    }  
    return text;
}

void loop() {
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval)
  {
    // put your main code here, to run repeatedly:
    cnt++;
    Serial.println(cnt);

    udpData = String(nodename + ","
                     + cnt
                    );

    UDPSend udp = AISnb.sendUDPmsgStr(serverIP, serverPort, udpData);
    previousMillis = currentMillis;
  }
  UDPReceive resp = AISnb.waitResponse();
  String b = resp.data;

  if (b != 0){
     Serial.print("data = ");
     String a;
     a = hexToString(b);
     Serial.println(a);
  }
  
}
