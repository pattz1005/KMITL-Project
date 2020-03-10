#include <Servo.h>

Servo servo;
int pos = 0;    // variable to store the servo position


void setup() {
  // put your setup code here, to run once:
  servo.attach(9);
  servo.write(0);

  
}

void loop() {
    // goes from 0 degrees to 180 degrees
    // in steps of 1 degree
    servo.write(180);              // tell servo to go to position in variable 'pos'
    delay(500);                       // waits 15ms for the servo to reach the position
  
  // goes from 180 degrees to 0 degrees
    servo.write(0);              // tell servo to go to position in variable 'pos'
    delay(500);                       // waits 15ms for the servo to reach the position
  
}
