#define LCD_CS A3 // Chip Select goes to Analog 3
#define LCD_CD A2 // Command/Data goes to Analog 2
#define LCD_WR A1 // LCD Write goes to Analog 1
#define LCD_RD A0 // LCD Read goes to Analog 0
#define LCD_RESET A4 // Can alternately just connect to Arduino's reset pin


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

  
  Wire.begin();

  QRCode qrcode;
  uint8_t qrcodeData[qrcode_getBufferSize(8)];
  qrcode_initText(&qrcode, qrcodeData, 8, 3, "https://www.teksol-enterprise.com/smartlocker/view/#/auth/login");

  tft.println("   Header");
  
   for (uint8_t y = 0; y < qrcode.size; y++) {
        // Each horizontal module
        for (uint8_t x = 0; x < qrcode.size; x++) {
            // Print each module (UTF-8 \u2588 is a solid block)
            Serial.print(qrcode_getModule(&qrcode, x, y) ? "\u2588\u2588": "  ");
      
            if(qrcode_getModule(&qrcode, x, y)){
              
              tft.fillRect( 10 + (x*6), 160 + (y*6), 6,6,BLACK);
              
            }
            
        }
        Serial.print("\n");
 
    }
    
}


void loop() {
  // put your main code here, to run repeatedly:

}
