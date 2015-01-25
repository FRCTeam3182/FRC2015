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
import org.usfirst.frc.team3182.robot.auto.AutoPossibilityInterface;
import org.usfirst.frc.team3182.robot.auto.DriverForwardPoss;

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
    public SmartDashboard dash;


    public static ArrayList<Class<? extends AutoPossibilityInterface>> possibilityClasses = new ArrayList<Class<? extends AutoPossibilityInterface>>();

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
        
    }

    public static void listOfPossibilities(){
        possibilityClasses.add(DriverForwardPoss.class);

        
    }

    /**
     * Called once when autonomous is triggered
     */
    @Override
    public void autonomousInit() {
<<<<<<< HEAD
        String test = "Driver Forward Possibility";
        AutoPossibilityInterface possibility = null;
        for (Class<? extends AutoPossibilityInterface> i : possibilityClasses){
            try {
                if (i.newInstance().getName().equalsIgnoreCase(test)){
                    possibility = i.newInstance();
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (possibility == null){
            return;
        }
        possibility.executePossibility(sensorsVar, lifterVar, driveTrainVar);

=======
    	
>>>>>>> b25b1f1c4af072462c6600cd1a5938a905a40310
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

}
