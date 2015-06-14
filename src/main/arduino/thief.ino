/*
  DigitalReadSerial
 Reads a digital input on pin 2, prints the result to the serial monitor 
 
 This example code is in the public domain.
 */

// digital pin 2 has a pushbutton attached to it. Give it a name:
int pushButton = 7;
int lastState = HIGH;
int status = LOW;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  // make the pushbutton's pin an input:
  pinMode(pushButton, INPUT);
  //Use pullup resistors
//  digitalWrite(pushButton, HIGH);
}

int count = 0;
int last = HIGH;

// the loop routine runs over and over again forever:
void loop() {
  // read the input pin:
  int buttonState = digitalRead(pushButton);
      if(last==HIGH) {
        count++;
        if(count>200) {
          count = 100;
        }
        
        if(count>40) {
          if(status!=HIGH) {
            status = HIGH;
            Serial.println("LED ON");
            count = 0;
          }
        }
      } else {
        if(status!=LOW) {
          status = LOW;
          Serial.println("LED OFF");
          count = -20;
        }
          count --;
      }
      
   last = buttonState;

  delay(1);        // delay in between reads for stability
}



