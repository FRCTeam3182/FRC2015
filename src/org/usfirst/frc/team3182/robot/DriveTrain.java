/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  
 *                          The DriveTrain								*
 * 																		*
 * This class is the thread for the drivetrain without shifters			*
 * 																		*
 * 																		*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/

package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

public class DriveTrain implements Runnable {


	private final DriverStation driverStation;

    private /*final*/ RobotDrive drive = null;  //why would this be final as null?

    private ArrayList<Encoder> encoders = new ArrayList<Encoder>();

    //private ArrayList<Talon> talons = new ArrayList<Talon>();

	// General direction commands (start at unmoving)
	private volatile double xCommand = 0; // 0 for unmoving, 1 for full strafe right, -1 for strafe left
	private volatile double yCommand = 0; // 0 for unmoving, 1 for full forward, -1 for backward
	private volatile double rotationCommand = 0; // 0 for unmoving, 1 for full clockwise, -1 counterclockwise

	// Joysticks
	private final Joystick driveJoystick;
	private volatile boolean joystickStateCommand = true; // false to disable joysticks

	// Deadzone and smoothing
	private double ySmooth = 0; // for making joystick output a linear function between P and 1 and -P to -1
	private double xSmooth = 0;
	private double rotationSmooth = 0;
	private final double P = 0.10; // dead zone of joysticks for drive is between -P and P
	private final double rotationP = 10; // dead zone for the joystick's rotation (in degrees)
    

	
	// Initialze gyro   
	private Gyro gyro;
	
	public DriveTrain() {

		// Initializing everything
		driverStation = DriverStation.getInstance();

		// Instantiate
		gyro = new Gyro(new AnalogInput(0));

		// Joystick
		driveJoystick = new Joystick(0);

//        talons.add(new Talon(0));
//        talons.add(new Talon(1));
//        talons.add(new Talon(2));
//        talons.add(new Talon(3));



        drive = new RobotDrive(0, 1, 2, 3);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        //drive.setSafetyEnabled(true);

        encoders.add(new Encoder(1, 2));
        encoders.add(new Encoder(3, 4));
        encoders.add(new Encoder(5, 6));
        encoders.add(new Encoder(7, 8)); // TODO Check all these ports
	}

	public void run() {
        while (true) {

            // If joystickStateCommand is true, get the joystick values
//            if (joystickStateCommand) {
//                if (driveJoystick.getRawButton(1)){
//               // 	moveDriveTrain_Speed(.3,.3,.3,.3);  //TODO Remove this because this is for testing only
//                	drive.mecanumDrive_Cartesian(0, .5, 0, 0);	
//                }
//                else {
//  //              	moveDriveTrain_Speed(0,0,0,0);
//                	drive.mecanumDrive_Cartesian(0, 0, 0, 0);
//                }
            	xCommand = driveJoystick.getAxis(Joystick.AxisType.kX);
            	yCommand = driveJoystick.getAxis(Joystick.AxisType.kY);
            	rotationCommand = driveJoystick.getTwist();
            
            if (driverStation.isEnabled()) {
                /*=================================================================
                -Makes sure joystick will not work at �P% throttle, P is declared above
                -smoothVarRight/Left are output variables from a function
                that calculates how much to power the motors
                -Full throttle always outputs a 1 (full power)
                -While joystick is in deadzone, a 0 is outputted
                 =================================================================*/

                //Deadzone
                if (yCommand < P && yCommand > (-P)) {
                    yCommand = 0;
                }
                if (xCommand < P && xCommand > (-P)) {
                    xCommand = 0;
                }

                //Smoothing
                //If the x is positive and passed the deadzone (joystick is moved to the right)
                if (xCommand >= P) {
                    xSmooth = ((1 / (1 - P)) * xCommand + (1 - (1 / (1 - P))));
                }
                // If the x is negative and passed the deadzone (joystick is moved to the left) 
                if (xCommand <= (-P)) {
                    xSmooth = ((1 / (1 - P)) * xCommand - (1 - (1 / (1 - P))));
                }
                // If the y is positive and passed the deadzone (joystick is moved to the up) 
                if (yCommand >= P) {
                    ySmooth = ((1 / (1 - P)) * yCommand + (1 - (1 / (1 - P))));
                }
                // If the y is negative and passed the deadzone (joystick is moved to the down) 
                if (yCommand <= (-P)) {
                    ySmooth = ((1 / (1 - P)) * yCommand - (1 - (1 / (1 - P))));
                }
                // If the rotation is positive and passed the deadzone (joystick is twisted clockwise) 
                if (rotationCommand >= rotationP) {
                    rotationSmooth = ((1 / (1 - rotationP)) * yCommand + (1 - (1 / (1 - P))));
                }
                // If the rotation is negative and passed the deadzone (joystick is twisted counterclockwise)
                if (yCommand <= rotationP) {
                    rotationSmooth = ((1 / (1 - rotationP)) * yCommand - (1 - (1 / (1 - P))));
                }


                moveDriveTrain(xCommand, yCommand, 0, gyro.getAngle());
                //moveDriveTrain(xSmooth, ySmooth, 0, gyro.getAngle());
//                moveDriveTrain_Speed(xCommand, xCommand, xCommand, xCommand);
            }
            driveToDashboard();
            Timer.delay(.1); //10ms delay
        }
	}


	public synchronized void setJoystickStateCommand(boolean joystickStateCommand) {
		this.joystickStateCommand = joystickStateCommand;
	}

//    public void moveDriveTrain_Speed(double frontLSpeed, double frontRSpeed, double rearLSpeed, double readRSpeed){
//        talons.get(0).set(frontLSpeed);
//        talons.get(1).set(-frontRSpeed);
//        talons.get(2).set(rearLSpeed);
//        talons.get(3).set(-readRSpeed);
//    }

    public void moveDriveTrain(double x, double y, double rotation){
        drive.mecanumDrive_Cartesian(x, y, rotation, 0);
    }

    public void moveDriveTrain(double x, double y, double rotation, double gyro){
        drive.mecanumDrive_Cartesian(x, y, rotation, gyro);
    }

    public void moveDriveTrainDistance(double distX, double distY)
    {
        //Moves the robot in an arbitrary distance unit, determined by the encoder's output
        //double oldDistance = 0.00;
        distX = distX * 1;
        distY = distY * 1; // TODO Change to correct values
        double xyRatio = distX/(distY+distX);
        double yxRatio = distY/(distY+distX);
        double totDistance = Math.sqrt(distX * distX + distY * distY); //pythagorean theorem
        double encoderXTotDist = 0;
        double encoderYTotDist = 0;
        for(Encoder e : encoders) {
            e.reset();
        }
        moveDriveTrain(xyRatio, yxRatio, 0, 0);
        do
        {
            encoderXTotDist -= encoders.get(0).getDistance() / 4;
            encoderXTotDist += encoders.get(2).getDistance() / 4;
            encoderXTotDist += encoders.get(1).getDistance() / 4;
            encoderXTotDist -= encoders.get(3).getDistance() / 4;
            encoderYTotDist += encoders.get(0).getDistance() / 4;
            encoderYTotDist += encoders.get(2).getDistance() / 4;
            encoderYTotDist += encoders.get(1).getDistance() / 4;
            encoderYTotDist += encoders.get(3).getDistance() / 4;

        }while(Math.sqrt(Math.pow(encoderXTotDist, 2) + Math.pow(encoderYTotDist, 2)) < totDistance);

        moveDriveTrain(0, 0, 0, 0);
    }


	private void driveToDashboard() {
		SmartDashboard.putNumber("Raw x Axis", xCommand);
		SmartDashboard.putNumber("Raw y Axis", yCommand);
		SmartDashboard.putNumber("Raw rotation", rotationCommand);
		SmartDashboard.putNumber("Smooth Var x Axis", xSmooth);
		SmartDashboard.putNumber("Smooth Var y Axis", ySmooth);
		SmartDashboard.putNumber("Smooth Var rotation", rotationSmooth);
		SmartDashboard.putBoolean("Joystick state", joystickStateCommand);

        for (Encoder e : encoders){
            SmartDashboard.putNumber("Encoder " + encoders.indexOf(e), e.getRate());
        }
	}


}