/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * FRC Team 3182 Athena's Warriors
 *
 * Our official code of complete awesomeness to control the universe. 
 * This year's game: Recycle Rush
 *#YOLOSWAG!!!!
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc.team3182.robot.test.motorTest.MotorTest;

public class Robot3182 extends IterativeRobot {

    //Declaring thread variables
    private static DriveTrain driveTrainVar;

    private ArduinoLights arduinoLightsVar;
    private static Lifter lifterVar;

    //Declaring dashboard variable
    public static SendableChooser table = new SendableChooser();

    // The variable for testing individual motors
    private MotorTest test;
    
   

    /**
     * Called once when the robot is turned on
     */
    public void robotInit() {
        
        //Initialize the threads

        //arduinoLightsVar = new ArduinoLights();
       // new Thread(arduinoLightsVar, "ArduinoLights").start();
        driveTrainVar = new DriveTrain();
        new Thread(driveTrainVar, "DriveTrain").start();
        lifterVar = new Lifter();
        new Thread(lifterVar, "Lifter").start();



        //arduinoLightsVar.setLightSequence(LightsEnum.RAINBOW);

    }

    /**
     * This adds all the possibility classes to the array list
     * and builds the SmartDashboard dropdown
     */

    /**
     * Called once when autonomous is triggered
     */
    @Override
    public void autonomousInit() {
    	
        driveTrainVar.setYCommand(-.8);
        Timer.delay(2.8);
        driveTrainVar.setYCommand(0);
    }

    @Override
    public void teleopInit() {
    	driveTrainVar.setJoystickStateCommand(true);
    }
    /**
     * Called every 10ms while in teleop
     */
    @Override
    public void teleopPeriodic() {

    }
    
    @Override
    public void disabledInit()
    {
    	driveTrainVar.setJoystickStateCommand(false);

        test.killThreads();
    }


    /**
     * Called when test mode is enabled
     */
    @Override
    public void testInit() {

        test = new MotorTest(driveTrainVar.getJoystick(), lifterVar.getJoystick());
        test.initiateTest();
    }


    public static DriveTrain getDriveTrain() {
        return driveTrainVar;
    }

    public static Lifter getLifter() {
        return lifterVar;
    }

    public ArduinoLights getArduinoLights() {
        return arduinoLightsVar;
    }


}
