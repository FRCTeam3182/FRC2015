/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * FRC Team 3182 Athena's Warriors
 *
 * Our official code of complete awesomeness to control the universe. 
 * This year's game: Recycle Rush
 *#YOLOSWAG!!!!
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3182.robot.auto.*;
import org.usfirst.frc.team3182.robot.lights.LightsEnum;

public class Robot3182 extends IterativeRobot {

    //Declaring thread variables
    private static DriveTrain driveTrainVar;

    private ArduinoLights arduinoLightsVar;
    private static Lifter lifterVar;
    private static UltrasonicSensor ultrasonicSensorVar;

    //Declaring dashboard variable
    public static SendableChooser table = new SendableChooser();
    
   

    /**
     * Called once when the robot is turned on
     */
    public void robotInit() {
        listOfPossibilities();

        //Initialize the threads

        //arduinoLightsVar = new ArduinoLights();
       // new Thread(arduinoLightsVar, "ArduinoLights").start();
        driveTrainVar = new DriveTrain();
        new Thread(driveTrainVar, "DriveTrain").start();
        lifterVar = new Lifter();
        new Thread(lifterVar, "Lifter").start();
        //ultrasonicSensorVar = new UltrasonicSensor(arduinoLightsVar);
       // new Thread(ultrasonicSensorVar, "UltraSonic").start();



        //arduinoLightsVar.setLightSequence(LightsEnum.RAINBOW);

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
    	
        driveTrainVar.setYCommand(-.8);
        Timer.delay(2.8);
        driveTrainVar.setYCommand(0);
        
        /*
        AutoPossibilityInterface possibility;
        possibility = (AutoPossibilityInterface) table.getSelected();
        possibility.sendLightsCommand(arduinoLightsVar);
        possibility.executePossibility(lifterVar, driveTrainVar);
        */
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
    }

    /**
     * Called when test mode is activated
     
    @Override
    public void testInit() {
        lifterVar.reset(); // Resets the lifter to the ready position
        driveTrainVar.testDriveTrain(); //for use while wheels off the ground
    
    }
    */
    @Override
    public void testPeriodic() {
        //lifterVar.reset(); // Resets the lifter to the ready position
        //driveTrainVar.testDriveTrain(); //for use while wheels off the ground
    	Joystick j = new Joystick(0);
    	lifterVar.moveMotor(0, j.getAxis(Joystick.AxisType.kY));
    	lifterVar.moveMotor(1, j.getAxis(Joystick.AxisType.kX));
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
