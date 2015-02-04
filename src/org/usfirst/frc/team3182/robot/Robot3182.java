/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * FRC Team 3182 Athena's Warriors
 *
 * Our official code of complete awesomeness to control the universe. 
 * This year's game: Recycle Rush
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3182.robot.auto.*;
import org.usfirst.frc.team3182.robot.util.DriverUtil;
import org.usfirst.frc.team3182.robot.util.LifterUtil;


public class Robot3182 extends IterativeRobot {

    //Declaring thread variables
    private DriveTrain driveTrainVar;
    private Sensors sensorsVar;
    private ArduinoLights arduinoLightsVar;
    private Lifter lifterVar;
    
    //Declaring joystick variable
    private Joystick buttonsJoystick;

    //Declaring dashboard variable
    public static SendableChooser table = new SendableChooser();

    private static DriverUtil du;
    private static LifterUtil lu;


    /**
     * Called once when the robot is turned on
     */
    public void robotInit() {
        listOfPossibilities();
        //Initialize the threads
        driveTrainVar = new DriveTrain();
        new Thread(driveTrainVar, "DriveTrain").start();
        sensorsVar = new Sensors();
        new Thread(sensorsVar, "Sensors").start();
        arduinoLightsVar = new ArduinoLights(sensorsVar);
        new Thread(arduinoLightsVar, "ArduinoLights").start();
        lifterVar = new Lifter(sensorsVar);
        new Thread(lifterVar, "Lifter").start();

        du = new DriverUtil();

        lu = new LifterUtil();


        
    }

    /**
     * This adds all the possibility classes to the array list
     * and builds the SmartDashboard dropdown
     */
    public static void listOfPossibilities(){
        DriveForwardPoss driveForwardPoss = new DriveForwardPoss();
        table.addDefault(driveForwardPoss.getName(), driveForwardPoss);

        PushTotePoss pushTotePoss = new PushTotePoss();
        table.addObject(pushTotePoss.getName(), pushTotePoss);

        PushBinPoss pushBinPoss = new PushBinPoss();
        table.addObject(pushBinPoss.getName(), pushBinPoss);

        UltimateBeautyPoss ultimateBeautyPoss = new UltimateBeautyPoss();
        table.addObject(ultimateBeautyPoss.getName(), ultimateBeautyPoss);

        SmartDashboard.putData("Autonomous Mode Chooser", table);
    }

    /**
     * Called once when autonomous is triggered
     */
    @Override
    public void autonomousInit() {
        AutoPossibilityInterface possibility;
        possibility = (AutoPossibilityInterface) table.getSelected();
        possibility.executePossibility(sensorsVar, lifterVar, driveTrainVar);

    }

    /**
     * Called every 10ms while in teleop
     */
    @Override
    public void teleopPeriodic() {

    }

    /**
     * Called when test mode is activated
     */
    @Override
    public void testInit() {
        lu.resetLifter(); // Resets the lifter to the ready position
    }

    public static DriverUtil getDriverUtil(){
        return du;
    }

    public static LifterUtil getLifterUtil() {
        return lu;
    }
}
