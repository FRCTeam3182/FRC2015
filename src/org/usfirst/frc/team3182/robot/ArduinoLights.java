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

	private boolean isAuto;
    private int oldDistance;
    private int distance;

    //declare instances
    private DriverStation driverStation;
    //private Sensors sensors;
    private SerialPort arduino;
    private Port port;

    public ArduinoLights() {
        //initializing everything
        arduino = new SerialPort(9600, port); //I2C line for transmitting data
        driverStation = DriverStation.getInstance();
    }

    public void run() {
        while (true) {
            //store all of the data to variables
            isAuto = driverStation.isAutonomous();
//            distance = sensors.shootingDistance();

            //how to send stuff
             
            
            //store old variables to be used later
            oldDistance = distance;
            
            //10ms loop delay
            Timer.delay(.01);
        }
    }
}
