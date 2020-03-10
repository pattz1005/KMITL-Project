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

  Serial.println(tft.width()); //int16_t width(void);
  Serial.println(tft.height()); //int16_t height(void);


  Wire.begin(0x71);
  Wire.onReceive(receiveEvent); // register event
  o_c = true;
  protocolUart = HEADER;
  state = QR;
  index = 0;

  wdt_enable(WDTO_8S);
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
  wdt_reset();
  if (queue.isEmpty()) {
    return;
  }
  uint8_t dataSize;
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
        protocolUart = RESET;
      }
      else if ( inputByte == 0x22) {
        Serial.println("command o/c");
        protocolUart = O_C;
      }
      else if ( inputByte == 0x23) {
        Serial.println("get 0x23");
        dataSize = queue.pop();
        qrSize = dataSize;
        protocolUart = SHOWQR;
      }
      else if (inputByte == 0x24) {
        Serial.println("get 0x24");
        protocolUart = SHOWTEXT;
      }
      else if (inputByte == 0x25) {
        Serial.println("get 0x25");
        protocolUart = SHOWTITLE;
      }
      break;

    case RESET :

      dataSize = queue.pop();
      inputByte = queue.pop();
      if ( dataSize == 0x01) {
        if (inputByte == 0x00) {
          command = 0x01;
          protocolUart = END;
        }

      } else {
        protocolUart = HEADER;
      }
      break;
    case O_C :

      dataSize = queue.pop();
      inputByte = queue.pop();
      if ( dataSize == 0x01) {
        if (inputByte == 0x00) {
          command = 0x11;
          protocolUart = END;
        }
        else if (inputByte == 0x01) {
          command = 0x12;
          protocolUart = END;
        }
      } else {
        protocolUart = HEADER;
      }
      break;

    case SHOWQR :

      
      Serial.print("size = ");
      Serial.println(dataSize);
      Serial.println("command qr");
      if ( dataSize >= 0x00 && dataSize <= 0xFF) {
        command = 0x03;
        for (uint8_t i = 0 ; i < dataSize ; i++) {
          qrData[index] = queue.pop();
          Serial.write(qrData[index]);
          index++;
        }
        Serial.println(queue.isEmpty());
        protocolUart = END;
      } else {
        protocolUart = HEADER;
      }
      break;

    case SHOWTEXT :

      dataSize = queue.pop();
      if ( dataSize >= 0x00 && dataSize <= 0xFF) {
        Serial.println("show text");
        textSize = dataSize;
        command = 0x04;
        for (int i = 0 ; i < dataSize ; i++) {
          textData[i] = queue.pop();
        }
        protocolUart = END;
      } else {
        protocolUart = HEADER;
      }
      break;

    case SHOWTITLE :
      // wdt_reset();
      dataSize = queue.pop();
      Serial.println("show title");
      if ( dataSize >= 0x00 && dataSize <= 0xFF) {
        titleSize = dataSize;
        command = 0x05;
        for (int i = 0 ; i < dataSize ; i++) {
          titleData[i] = queue.pop();
        }
        protocolUart = END;
      } else {
        protocolUart = HEADER;
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
    Serial.print("A");
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
  tft.setCursor(1, 0);
  tft.setTextColor(BLACK);
  for (int i = 0; i < titleSize; i++) {
    tft.write(titleData[i]);
  }
}

void printQR() {
  if ( o_c == false) {
    return;
  }
  index = 0;
  QRCode qrcode;
  uint8_t qrcodeData[qrcode_getBufferSize(6)];
  qrcode_initBytes(&qrcode, qrcodeData, 6, 3, qrData, qrSize);
  for (uint8_t y = 0; y < qrcode.size; y++) {
    // Each horizontal module

    for (uint8_t x = 0; x < qrcode.size; x++) {
      // Print each module (UTF-8 \u2588 is a solid block)
      wdt_reset();
      Serial.print(qrcode_getModule(&qrcode, x, y) ? "\u2588\u2588" : "  ");

      if (qrcode_getModule(&qrcode, x, y)) {
         wdt_reset();
        tft.fillRect( 40 + (x * 6), 160 + (y * 6), 6, 6, BLACK);

      }

    }
    Serial.println();
  }

}

void printText() {
  if ( o_c == false) {
    return;
  }
  tft.setTextSize(2);
  tft.setCursor(1, 150);
  tft.setTextColor(BLACK);
  for (int i = 0 ; i < textSize; i++) {

    tft.write(textData[i]);
  }

}

void clearTop() {
  tft.reset();
  tft.fillRect(0, 0, 320, 120, WHITE);
}

void clearBot() {
  tft.reset();
  tft.fillRect(0, 120, 320, 360, WHITE);
}
