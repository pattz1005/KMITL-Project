#include <QueueList.h>
#include "qrcode.h"
#include "Wire.h"
#include <Adafruit_GFX.h> // Hardware-specific library
#include <MCUFRIEND_kbv.h>
#include <avr/wdt.h>

#define BLACK   0x0000
#define BLUE    0x001F
#define RED     0xF800
#define GREEN   0x07E0
#define CYAN    0x07FF
#define MAGENTA 0xF81F
#define YELLOW  0xFFE0
#define WHITE   0xFFFF




enum Protocol {
  HEADER,
  COMMAND,
  RESET,
  O_C,
  SHOWTEXT,
  SHOWQR,
  SHOWTITLE,
  SIZE,
  END
};

enum State {
  QR,
  TEXT
};

MCUFRIEND_kbv tft;
uint16_t g_identifier;
QueueList <uint8_t> queue;
Protocol protocolUart;
State state;
uint8_t command;
uint8_t qrSize;
uint8_t textSize;
uint8_t titleSize;
uint8_t qrData[128];
uint8_t textData[128];
uint8_t titleData[128];
uint8_t inputByte;
uint8_t index;
uint8_t dataSize;
bool o_c;


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  g_identifier = tft.readID();

  Serial.print("ID = 0x");
  Serial.println(g_identifier, HEX);

  tft.begin(g_identifier);
  tft.fillScreen(WHITE); //fillScreen(uint16_t t);
  tft.setTextSize(5);
  tft.setTextColor(BLACK);
  tft.setCursor(0, 0);
  tft.setRotation(0);

  Wire.begin(0x71);
  Wire.onReceive(receiveEvent); // register event
  o_c = true;
  protocolUart = HEADER;
  state = QR;
  index = 0;

  //wdt_enable(WDTO_8S);
}

void loop() {
  // put your main code here, to run repeatedly:
  getProtocol();
}

void receiveEvent(int howMany) {

  while (1 < Wire.available()) { // loop through all
    uint8_t c = Wire.read(); // receive byte as a character
    queue.push(c);
    // print the character
  }
  uint8_t x = Wire.read();
  queue.push(x);


}


void(* resetFunc) (void) = 0; //declare reset function @ address 0


void getProtocol() {
  //wdt_reset();
  if (queue.isEmpty()) {
    return;
  }
  switch (protocolUart) {
    case HEADER :
      inputByte = queue.pop();

      Serial.println("header");
      if (inputByte == 0x02) {
        protocolUart = COMMAND;
      }
      break;
    case COMMAND :

      inputByte = queue.pop();
      Serial.println("command");
      Serial.println(inputByte);
      if (inputByte == 0x21) {
        command = 0x21;

      }
      else if ( inputByte == 0x22) {
        Serial.println("command o/c");
        command = 0x22;

      }
      else if ( inputByte == 0x23) {
        Serial.println("get 0x23");
        command = 0x23;

      }
      else if (inputByte == 0x24) {
        Serial.println("get 0x24");
        command = 0x24;

      }
      else if (inputByte == 0x25) {
        Serial.println("get 0x25");
        command = 0x25;

      }
      else {
        protocolUart = HEADER;
      }
      protocolUart = SIZE;
      break;

    case SIZE :
      Serial.println("size state");
      dataSize = queue.pop();
      Serial.print("size = ");
      Serial.println(dataSize);

      if (command == 0x21) {
        if (dataSize == 0x01) {
          protocolUart = RESET;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else if (command == 0x22) {
        if (dataSize == 0x01) {
          protocolUart = O_C;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else if (command == 0x23) {
        if ( dataSize >= 0x00 && dataSize <= 0xFF) {
          protocolUart = SHOWQR;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else if (command == 0x24) {
        if ( dataSize >= 0x00 && dataSize <= 0xFF) {
          protocolUart = SHOWTEXT;
        }
        else {
          protocolUart = HEADER;
        }
      }
      else if (command == 0x25) {
        if ( dataSize >= 0x00 && dataSize <= 0xFF) {
          protocolUart = SHOWTITLE;
        }
        else {
          protocolUart = HEADER;
        }
      }
      break;

    case RESET :
      inputByte = queue.pop();
      if (inputByte == 0x00) {
        command = 0x01;
        protocolUart = END;
      } else {
        protocolUart = HEADER;
      }
      break;

    case O_C :
      inputByte = queue.pop();

      if (inputByte == 0x00) {
        command = 0x11;
        protocolUart = END;
      }
      else if (inputByte == 0x01) {
        command = 0x12;
        protocolUart = END;
      }
      else {
        protocolUart = HEADER;
      }
      break;

    case SHOWQR :

      command = 0x03;
      if (index < dataSize) {
        qrData[index] = queue.pop();
        Serial.write(qrData[index]);
        index++;
      }
      else {
        protocolUart = END;
      }
      
      break;

    case SHOWTEXT :
      Serial.println("show text");
      command = 0x04;
      if (index < dataSize) {
        textData[index] = queue.pop();
        Serial.write(qrData[index]);
        index++;
      }

      else {
        protocolUart = END;
      }
      break;

    case SHOWTITLE :
      // //wdt_reset();
      Serial.println("show title");
      Serial.print("size = ");
      Serial.println(dataSize);
      command = 0x05;
      if (index < dataSize) {
        titleData[index] = queue.pop();
        index++;
      }
      else {
        protocolUart = END;
      }
      break;

    case END :

      inputByte = queue.pop();
      if (inputByte == 0x03) {
        Serial.println("end state");
        processProtocol();
      }
      protocolUart = HEADER;
      break;
  }


}

void processProtocol() {

  if (command == 0x01) {
    resetFunc();
  }
  else if (command == 0x11) {
    //off
    offDisplay();
  }
  else if (command == 0x12) {
    //on
    onDisplay();
  }
  else if (command == 0x03) {
    //show qr
    Serial.println("p QR");
    state = QR;
    clearBot();
    printQR();
  }
  else if (command == 0x04) {
    //show text
    Serial.println("p title");
    state = TEXT;
    clearBot();
    printText();
  }

  else if (command == 0x05) {
    //set title
    clearTop();
    printHeader();

  }
}

void onDisplay() {

  if ( o_c == false) {
    tft.reset();
    tft.fillScreen(WHITE);
    o_c = true;
  }
}

void offDisplay() {

  if ( o_c == true) {
    tft.reset();
    tft.fillScreen(BLACK);
    o_c = false;
  }
}

void printHeader() {
  if ( o_c == false) {
    return;
  }
  tft.setTextSize(4);
  //tft.setCursor(35, 0);
  tft.setCursor(0, 0);
  tft.setTextColor(BLACK);
  for (int i = 0; i < index; i++) {
    tft.write(titleData[i]);
  }
  index = 0;
}

void printQR() {
  if ( o_c == false) {
    return;
  }
  Serial.println("printing qr");
  QRCode qrcode;
  uint8_t qrcodeData[qrcode_getBufferSize(6)];
  qrcode_initBytes(&qrcode, qrcodeData, 6, 0, qrData, dataSize);
  for (uint8_t y = 0; y < qrcode.size; y++) {
    // Each horizontal module
     //wdt_reset();
     
    for (uint8_t x = 0; x < qrcode.size; x++) {
      // Print each module (UTF-8 \u2588 is a solid block)
      //wdt_reset();

      if (qrcode_getModule(&qrcode, x, y)) {
        //wdt_reset();
        tft.fillRect( 18 + (x * 7), 160 + (y * 7), 7, 7, BLACK);

      }

    }
    
  }
  index = 0;

}

void printText() {
  if ( o_c == false) {
    return;
  }
  tft.setTextSize(3);
  tft.setCursor(35, 180);
  tft.setTextColor(BLACK);
  for (int i = 0 ; i < index; i++) {

    tft.write(textData[i]);
  }
  index = 0;

}

void clearTop() {
  tft.reset();
  tft.fillRect(0, 0, 320, 120, WHITE);
}

void clearBot() {
  tft.reset();
  tft.fillRect(0, 120, 320, 360, WHITE);
}
