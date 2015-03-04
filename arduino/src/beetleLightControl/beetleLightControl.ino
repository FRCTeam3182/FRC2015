/* BeetleLightControl by Team 3182
 *
 * A program that recieves information from the RoboRIO and commands
 * a strip of LEDs based off of set commands
 *
 * Commands:
 *
 * FYI This will be awesome
 *
 *
 *
 */

#include <Adafruit_NeoPixel.h>

struct RGB {
  byte r;
  byte g;
  byte b;
};

int currentID = -1;
boolean isTopLit = false;
RGB topColor = {0, 0, 0};

void setup() {
  Serial.begin(9600);
}

void loop() {

}

void wait(int wait) {
  int tempMillis = millis();
  while (millis() - tempMillis < wait) {
    if (Serial.available() > 0) { //If something is received
      if (Serial.
    }
  else {
    delay(10);
    }
  }
}
