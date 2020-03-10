#include "TEE_UC20.h"
#include "SoftwareSerial.h"
#include <AltSoftSerial.h>
#include "internet.h"
#include "uc_mqtt.h"
INTERNET net;
UCxMQTT mqtt;
//SIM TRUE  internet
#define APN "internet"
#define USER ""
#define PASS ""

#define MQTT_SERVER      ""
#define MQTT_PORT        ""
#define MQTT_ID          "3G"
#define MQTT_USER        ""
#define MQTT_PASSWORD    ""


unsigned long previousmqtt = 0;
const long intervalmqtt = 5000;

AltSoftSerial mySerial;

void debug(String data)
{
  Serial.println(data);
}
void setup() 
{
  Serial.begin(9600);
  gsm.begin(&mySerial,9600);
  gsm.Event_debug = debug;
  Serial.println(F("UC20"));
  gsm.PowerOn(); 
  while(gsm.WaitReady()){}
  Serial.print(F("GetOperator --> "));
  Serial.println(gsm.GetOperator());
  Serial.print(F("SignalQuality --> "));
  Serial.println(gsm.SignalQuality());
  
  Serial.println(F("Disconnect net"));
  net.DisConnect();
  Serial.println(F("Set APN and Password"));
  net.Configure(APN,USER,PASS);
  Serial.println(F("Connect net"));
  net.Connect();
  Serial.println(F("Show My IP"));
  Serial.println(net.GetIP());
  mqtt.callback = callback;
 // mqtt.Publish("ctrl","hello test");
  connect_server();
}

void callback(String topic ,char *payload,unsigned char length)
{
  Serial.println();
  Serial.println(F("%%%%%%%%%%%%%%%%%%%%%%%%%%%%"));
  Serial.print(F("Topic --> "));
  Serial.println(topic);
  payload[length]=0;
  String str_data(payload);
  Serial.print(F("Payload --> "));
  Serial.println(str_data);
}
void connect_server()
{
  do
  {
     Serial.println(F("Connect Server"));
     Serial.println(F("wait connect"));
      if(mqtt.DisconnectMQTTServer())
      {
        mqtt.ConnectMQTTServer(MQTT_SERVER,MQTT_PORT);
      }
      delay(500);
      Serial.println(mqtt.ConnectState());
  }
 while(!mqtt.ConnectState());
 Serial.println(F("Server Connected"));
 unsigned char ret = mqtt.Connect(MQTT_ID,MQTT_USER,MQTT_PASSWORD);
 Serial.println(mqtt.ConnectReturnCode(ret));
 mqtt.Publish("ctrl",4,"hello world",11);
 mqtt.Publish("ctrl","hello world");
 mqtt.Subscribe("inTopic",7);
 
}
unsigned char check_con_cnt=0;
unsigned char cnty=0;
void loop() 
{
  unsigned long currentMillis = millis();
  if(currentMillis - previousmqtt >= intervalmqtt) 
  {
    previousmqtt = currentMillis;
      mqtt.Publish("ctrl","thaieasyelec");
  }  
  mqtt.MqttLoop();
  if(!mqtt.ConnectState())
     connect_server();
  
}

