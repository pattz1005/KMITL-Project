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

const long interval = 10000;  //millisecond
unsigned long previousMillis = 0;

//SIM TRUE  internet
#define APN "internet"
#define USER ""
#define PASS ""


//AltSoftmySerial mymySerial;
SoftwareSerial mySerial(8,9);

//mySerial mymySerial;

void debug(String data)
{
  mySerial.println(data);
}
void setup()
{
 // mySerial.begin(9600);
  mySerial.begin(9600);
  gsm.begin(&Serial, 9600);
  gsm.Event_debug = debug;
  cnt = 0;
  mySerial.println(F("UC20"));
  gsm.PowerOn();
  while (gsm.WaitReady()) {}

 mySerial.print(F("GetOperator --> "));
  mySerial.println(gsm.GetOperator());
  mySerial.print(F("SignalQuality --> "));
  mySerial.println(gsm.SignalQuality());

  mySerial.println(F("Disconnect net"));
  net.DisConnect();
  mySerial.println(F("Set APN and Password"));
  net.Configure(APN, USER, PASS);
 mySerial.println(F("Connect net"));
  net.Connect();
  mySerial.println(F("Show My IP"));
  mySerial.println(net.GetIP());
  mySerial.print(F("GetOperator --> "));
  mySerial.println(gsm.GetOperator());
  mySerial.print(F("SignalQuality --> "));
  mySerial.println(gsm.SignalQuality());
  mySerial.println(tcp.CheckConnection());
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
    mySerial.println("TCP sent");
    tcp.print(payload);
    tcp.StopSend();
    mySerial.println("Stop");
     mySerial.println("TCP Close");
    // tcp.Close();

  }
}
void open_tcp()
{
  mySerial.println();
  mySerial.println(F("Connect Server"));
  bool ret = tcp.Open("139.59.242.154", "5007");
}

void read_tcp() {
  int len = tcp.ReadBuffer();
  mySerial.println(len);
 while (len)
  {
    if (gsm.available())
    {
      mySerial.write(gsm.read());
      len--;
    }
  }
 mySerial.println();
  mySerial.println("Finish");
}
