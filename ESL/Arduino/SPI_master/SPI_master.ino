#include <SPI.h>
char buf [14];
volatile byte pos=0;
void setup (void)
{
Serial.begin (115200); 
  digitalWrite(SS, HIGH);  // ensure SS stays high for now
  SPI.begin ();

  SPI.setClockDivider(SPI_CLOCK_DIV128);
  
}  // end of setup
void loop (void)
{
  digitalWrite(SS, LOW);    // SS is pin 10
delay (1);
  for (int i=0;i<14;i++)
    buf[i]=SPI.transfer(0);
  // disable Slave Select
  digitalWrite(SS, HIGH);
  Serial.println(buf);

}  // end of loop
