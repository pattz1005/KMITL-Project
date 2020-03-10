#define aa 3
int i;
void setup() {
  // put your setup code here, to run once:
  pinMode(aa, OUTPUT);
  i = 0;
}

void loop() {
  // put your main code here, to run repeatedly:
  analogWrite(aa, 0);
  delay(1000);
  //lowToHigh();
  analogWrite(aa, 10);
  delay(1000);
  analogWrite(aa, 20);
  delay(1000);
  analogWrite(aa, 30);
  delay(1000);
  analogWrite(aa, 40);
  delay(1000);

}
void lowToHigh() {
  for(i = 0; i <256;i++){
    analogWrite(aa, i);
    delay(15);
  }
}
