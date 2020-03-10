#include "qrcode.h"
#include "Wire.h"
#include <Adafruit_GFX.h> // Hardware-specific library
#include <MCUFRIEND_kbv.h>

MCUFRIEND_kbv tft;


#define BLACK   0x0000
#define BLUE    0x001F
#define RED     0xF800
#define GREEN   0x07E0
#define CYAN    0x07FF
#define MAGENTA 0xF81F
#define YELLOW  0xFFE0
#define WHITE   0xFFFF

uint16_t g_identifier;
enum Protocol {
  START,
  COMMAND,
  LENGTH,
  DATA,
  END
};

Protocol protocolUart;
uint8_t command;

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



}

void loop() {
  // put your main code here, to run repeatedly:
  delay(100);
}

void receiveEvent(int howMany) {
  while (1 < Wire.available()) { // loop through all but the last
    char c = Wire.read(); // receive byte as a character
    Serial.print(c);         // print the character
  }
  char x = Wire.read();    // receive byte as an integer
  Serial.println(x);         // print the integer
  String xx;
  xx = String(xx + x);
  Serial.println(xx);

  qr_generate(xx);
}

void qr_generate(String xx) {
  QRCode qrcode;
  uint8_t buf[xx.length()];
  for (int i = 0 ; i < xx.length(); i++) {
    buf[i] = xx.charAt(i);
  }
  Serial.println(xx.length());

  Serial.println(buf[0]);


  uint8_t qrcodeData[qrcode_getBufferSize(6)];
  //qrcode_initText(&qrcode, qrcodeData, 5, 3, );
  qrcode_initBytes(&qrcode, qrcodeData, 6, 3, buf, xx.length());

  tft.reset();
  tft.fillScreen(WHITE);
  tft.setCursor(0, 0);
  tft.print("   Header");

  for (uint8_t y = 0; y < qrcode.size; y++) {
    // Each horizontal module
    for (uint8_t x = 0; x < qrcode.size; x++) {
      // Print each module (UTF-8 \u2588 is a solid block)
      Serial.print(qrcode_getModule(&qrcode, x, y) ? "\u2588\u2588" : "  ");

      if (qrcode_getModule(&qrcode, x, y)) {

        tft.fillRect( 40 + (x * 6), 160 + (y * 6), 6, 6, BLACK);

      }

    }
    Serial.println();

  }

}
