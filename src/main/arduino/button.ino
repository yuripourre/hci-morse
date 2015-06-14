/*
 * Button example with serial
 */
 
// Connect the button to pin 2 in arduino
const int buttonPin = 2;
// Pin 13 has an LED connected on most Arduino boards.
const int ledPin = 13;
//The button state (0 = released, HIGH = pressed)
int buttonState = LOW;
int lastState = LOW;

// the setup routine runs once when you press reset:
void setup() {
  // initialize the digital pin as an output.
  pinMode(ledPin, OUTPUT);
  // initialize the button pin as an input.
  // connect the button in VDD and PIN 2
  pinMode(buttonPin, INPUT);
  Serial.begin(9600);
}

// the loop routine runs over and over again forever:
void loop() {
    // read the state of the pushbutton value:
  buttonState = digitalRead(buttonPin);

  // check if the pushbutton is pressed.
  // if it is, the buttonState is HIGH:
  if(buttonState != lastState) {
    if (buttonState == HIGH) {
      // turn LED on:
      digitalWrite(ledPin, HIGH);
      Serial.println("LED ON");  // send a message via serial interface
    } else {
      // turn LED off:
      digitalWrite(ledPin, LOW);
      Serial.println("LED OFF"); // send a message via serial interface
    }
    lastState = buttonState;
  }
}
