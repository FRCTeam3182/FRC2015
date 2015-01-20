/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * FRC Team 3182 Athena's Warriors
 *
 * Our official code of complete awesomeness to control the universe. 
 * This year's game: Recycle Rush
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot3182 extends IterativeRobot {

    //Declaring thread variables
    private DriveTrain driveTrainVar;
    private Sensors sensorsVar;
    
    //Declaring joystick variable
    private Joystick buttonsJoystick;

    //Declaring dashboard variable
    public SmartDashboard dash;

    /**
     * Called once when the robot is turned on
     */
    public void robotInit() {
        //Initialize the threads
        driveTrainVar = new DriveTrain();
        new Thread(driveTrainVar, "DriveTrain").start();
        sensorsVar = new Sensors();
        new Thread(sensorsVar, "Sensors").start();
    }

    /**
     * Called once when autonomous is triggered
     */
    public void autonomousInit() {

    }

    /**
     * Called every 10ms while in teleop
     */
    public void teleopPeriodic() {

    }

    /**
     * Called every 10ms while in test mode
     */
    public void testPeriodic() {

    }

}
