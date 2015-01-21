/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * 						ArduinoLights
 * 
 * This class controls a strip of WS2812b addressable RGB LEDs. 
 * The animations on the strip are determined by the robot's actions
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/

package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;


public class ArduinoLights extends Object implements Runnable {

	private boolean isAuto;
    private int oldDistance;
    private int distance;

    //declare instances
    private DriverStation driverStation;
    private Sensors sensors;
    private I2C arduino;
    private Port port;

    public ArduinoLights(Sensors sensors) {
        //initializing everything
        arduino = new I2C(port, 69); //I2C line for transmitting data
        driverStation = DriverStation.getInstance();
        this.sensors = sensors;
    }

    public void run() {
        while (true) {
            //store all of the data to variables
            isAuto = driverStation.isAutonomous();
            distance = sensors.shootingDistance();

            //how to send stuff
            arduino.write(69, 0b1);
            
            //store old variables to be used later
            oldDistance = distance;
            
            //10ms loop delay
            Timer.delay(.01);
        }
    }
}
