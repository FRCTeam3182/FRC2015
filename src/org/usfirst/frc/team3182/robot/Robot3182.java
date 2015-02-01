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
import edu.wpi.first.wpilibj.tables.ITable;
import org.usfirst.frc.team3182.robot.auto.AutoPossibilityInterface;
import org.usfirst.frc.team3182.robot.auto.DriveForwardPoss;
import org.usfirst.frc.team3182.robot.auto.PushTotePoss;
import org.usfirst.frc.team3182.robot.util.DriverUtil;

import java.util.ArrayList;

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

    public static DriverUtil du;


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
     * Called every 10ms while in test mode
     */
    @Override
    public void testPeriodic() {

    }

    public static DriverUtil getDriverUtil(){
        return du;
    }


}
