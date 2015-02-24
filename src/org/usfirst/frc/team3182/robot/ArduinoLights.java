/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * 						ArduinoLights
 * 
 * This class controls a strip of WS2812b addressable RGB LEDs by 
 * sending information to an ATMega via Serial. 
 * The animations on the strip are determined by the robot's actions
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/

package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;


public class ArduinoLights implements Runnable {


    private SerialPort arduino;
    private Port port;

    public ArduinoLights() {
        //initializing everything
        arduino = new SerialPort(9600, port); //I2C line for transmitting data
    }

    public void run() {
        while (true) {

        }
    }

    public synchronized void setLightSequence(LightsEnum selection){
        if (selection == LightsEnum.RANDOM){
            randomSequence();
        }
        else if (selection == LightsEnum.PATTERN){
            patternSequence();
        }
        else if (selection == LightsEnum.WITH_LIFTER){
            withLifterSequence();
        }
    }

    private void randomSequence(){
        // TODO Add light code for random sequence
    }

    private void patternSequence(){
        // TODO Add pattern sequence
    }

    private void withLifterSequence(){
        //TODO Add lifter sequence
    }
}
