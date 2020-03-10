#include <Wire.h>
#include <LiquidCrystal_I2C.h>

  LiquidCrystal_I2C lcd(0x27, 16, 2);
  float input_voltage = 0.0;
  float temp=0.0;
  float r1=90900.0;
  float r2=10000.0;
  
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);     //  opens serial port, sets data rate to 9600 bps
  lcd.begin();
  lcd.print("DIGITAL VOLTMETER");


}

void loop() {
  // put your main code here, to run repeatedly:
  int analog_value = analogRead(A0);
   temp = (analog_value * 4.9) / 1024.0; 
   input_voltage = (temp / (r2/(r1+r2)))*9.3;
   
   if (input_voltage < 0.1) 
   {
     input_voltage=0.0;
   } 
    Serial.print("v= ");
    Serial.println(input_voltage);
    lcd.setCursor(0, 1);
    lcd.print("Voltage= ");
    lcd.print(input_voltage);
    delay(300);
}
