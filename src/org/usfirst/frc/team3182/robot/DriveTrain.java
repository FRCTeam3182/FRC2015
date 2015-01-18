/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *                          The DriveTrain
 * 
 * This class is the thread for the drivetrain without shifters
 * 
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Object implements Runnable {

    private final RobotDrive drive;
    private final DriverStation driverStation;
    
    //Commands
    private volatile double rightMotorCommand;
    private volatile double leftMotorCommand;
    boolean forwardCommand;
    boolean backwardCommand;
    
    //Joysticks
    private final Joystick rightJoystick;
    private final Joystick leftJoystick;
    private volatile boolean joystickStateCommand;
    
    //Deadzone and smoothing
    private double smoothVarRight = 0; //for making joysticks linear function between of zero to 1
    private double smoothVarLeft = 0;
    private final double P = 0.10; //dead zone of joysticks for drive is between -P and P

    public DriveTrain() {
        //initializing everything
        
        driverStation = DriverStation.getInstance();
        
        //drivetrain
        drive = new RobotDrive(1, 2);
        drive.setSafetyEnabled(false);
        
        //Commands
        rightMotorCommand = 0;
        leftMotorCommand = 0;
        joystickStateCommand = false;    
        
        //Joystick
        rightJoystick = new Joystick(1);
        leftJoystick = new Joystick(2);
    }

    public void run() {

        while (true) {
            
            //if joystickStateCommand is true, initialize all this stuff
            if (joystickStateCommand) {
                rightMotorCommand = rightJoystick.getAxis(Joystick.AxisType.kY);
                leftMotorCommand = leftJoystick.getAxis(Joystick.AxisType.kY);
            }
            if (driverStation.isEnabled()) {
                /*=================================================================
                -Makes sure joystick will not work at ±P% throttle, P is declared above
                -smoothVarRight/Left are output variables from a function
                that calculates how much to power the motors
                -Full throttle always outputs a 1 (full power)
                -While joystick is in deadzone, a 0 is outputted
                 =================================================================*/
                
                //Deadzone
                if (rightMotorCommand < P && rightMotorCommand > (-P)) {
                    smoothVarRight = 0;
                }
                if (leftMotorCommand < P && leftMotorCommand > (-P)) {
                    smoothVarLeft = 0;
                }
                
                //Smoothing
                // yAxisLeft greater than P, which is pull back on the joystick
                if (leftMotorCommand >= P) {
                    smoothVarLeft = ((1 / (1 - P)) * leftMotorCommand + (1 - (1 / (1 - P))));
                }
                // yAxisLeft less than -P, which is push forward on the joystick 
                if (leftMotorCommand <= (-P)) {
                    smoothVarLeft = ((1 / (1 - P)) * leftMotorCommand - (1 - (1 / (1 - P))));
                }
                // yAxisRight greater than P, which is pull back on the joystick 
                if (rightMotorCommand >= P) {
                    smoothVarRight = ((1 / (1 - P)) * rightMotorCommand + (1 - (1 / (1 - P))));
                }
                // yAxisLeft less than -P, which is push forward on the joystick 
                if (rightMotorCommand <= (-P)) {
                    smoothVarRight = ((1 / (1 - P)) * rightMotorCommand - (1 - (1 / (1 - P))));
                }
                
                //drive using the joysticks
                drive.tankDrive(-smoothVarLeft, -smoothVarRight);
            }
            driveToDashboard();
            Timer.delay(.1); //10ms delay
        }
    }

    
    public synchronized void setJoystickStateCommand(boolean joystickStateCommand) {
        this.joystickStateCommand = joystickStateCommand;
    }

    public synchronized void setRightMotorCommand(double rightMotorCommand) {
        this.rightMotorCommand = rightMotorCommand;
    }

    public synchronized void setLeftMotorCommand(double leftMotorCommand) {
        this.leftMotorCommand = leftMotorCommand;
    }

   

    private void driveToDashboard() {
        SmartDashboard.putNumber("leftMotorCommand", leftMotorCommand);
        SmartDashboard.putNumber("rightMotorCommand", rightMotorCommand);
        SmartDashboard.putNumber("Smooth Var Left", smoothVarLeft);
        SmartDashboard.putNumber("Smooth Var Right", smoothVarRight);
        SmartDashboard.putBoolean("Joystick state", joystickStateCommand);
    }
}