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
import org.usfirst.frc.team3182.robot.lights.LightsEnum;


public class ArduinoLights implements Runnable {

    private SerialPort arduino;

    private String deliminator = "|";

    public ArduinoLights() {
        //initializing everything
        arduino = new SerialPort(9600, Port.kMXP); //Serial port for transmitting data
    }

    public void run() {
        while (true) {

        }
    }

    public synchronized void setLightSequence(LightsEnum selection){
       sendID(selection.getID(), selection.getData());
    }

   private void sendID(int id, String data){
       arduino.writeString(String.valueOf(id) + deliminator + data);
   }
}
