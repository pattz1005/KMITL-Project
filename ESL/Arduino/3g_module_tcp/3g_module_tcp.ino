#include "TEE_UC20.h"
#include "SoftwareSerial.h"
#include <AltSoftSerial.h>
#include "internet.h"
#include "tcp.h"

INTERNET net;
TCP tcp;
int cnt;
String nodeName = "3G Module-test";
String payload;

const long interval = 1800000;  //millisecond
unsigned long previousMillis = 0;

//SIM TRUE  internet
#define APN "internet"
#define USER ""
#define PASS ""


AltSoftSerial mySerial;

void debug(String data)
{
  Serial.println(data);
}
void setup()
{
  Serial.begin(9600);
  gsm.begin(&mySerial, 9600);
  gsm.Event_debug = debug;
  cnt = 0;
  Serial.println(F("UC20"));
  gsm.PowerOn();
  while (gsm.WaitReady()) {}

  Serial.print(F("GetOperator --> "));
  Serial.println(gsm.GetOperator());
  Serial.print(F("SignalQuality --> "));
  Serial.println(gsm.SignalQuality());

  Serial.println(F("Disconnect net"));
  net.DisConnect();
  Serial.println(F("Set APN and Password"));
  net.Configure(APN, USER, PASS);
  Serial.println(F("Connect net"));
  net.Connect();
  Serial.println(F("Show My IP"));
  Serial.println(net.GetIP());
  Serial.print(F("GetOperator --> "));
  Serial.println(gsm.GetOperator());
  Serial.print(F("SignalQuality --> "));
  Serial.println(gsm.SignalQuality());
  Serial.println(tcp.CheckConnection());
  open_tcp();
}
void loop()
{
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval)
  {
 
    send_tcp();
    previousMillis = currentMillis;
  }

  if (tcp.ReceiveAvailable() == true) {
    read_tcp();
  }




  delay(1);

}
void send_tcp()
{
  if (tcp.StartSend())
  {

    cnt++;
    payload = String(nodeName + ","
                     + cnt);
    Serial.println("TCP sent");
    tcp.print(payload);
    tcp.StopSend();
    Serial.println("Stop");
    // Serial.println("TCP Close");
    // tcp.Close();

  }
}
void open_tcp()
{
  Serial.println();
  Serial.println(F("Connect Server"));
  bool ret = tcp.Open("139.59.242.154", "5007");
}

void read_tcp() {
  int len = tcp.ReadBuffer();
  Serial.println(len);
  while (len)
  {
    if (gsm.available())
    {
      Serial.write(gsm.read());
      len--;
    }
  }
  Serial.println();
  Serial.println("Finish");
}
