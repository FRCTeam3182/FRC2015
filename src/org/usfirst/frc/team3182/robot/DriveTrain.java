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

    private final RobotDrive drive;

    private ArrayList<Encoder> encoders = new ArrayList<Encoder>();

	// General direction commands (start at unmoving)
	private volatile double xCommand = 0; // 0 for unmoving, 1 for full strafe right, -1 for strafe left
	private volatile double yCommand = 0; // 0 for unmoving, 1 for full forward, -1 for backward
	private volatile double rotationCommand = 0; // 0 for unmoving, 1 for full clockwise, -1 counterclockwise

	// Joysticks
	private final Joystick driveJoystick;
	private volatile boolean joystickStateCommand; // false to disable joysticks

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
		driveJoystick = new Joystick(1);



        drive = new RobotDrive(0, 1, 2, 3); // TODO Check the motor direction
        drive.setSafetyEnabled(false);

        encoders.add(new Encoder(1,2));
        encoders.add(new Encoder(3,4));
        encoders.add(new Encoder(5,6));
        encoders.add(new Encoder(7,8)); // TODO Check all these ports
	}

	public void run() {
        while (true) {

            // If joystickStateCommand is true, get the joystick values
            if (joystickStateCommand) {
            	xCommand = driveJoystick.getAxis(Joystick.AxisType.kX); ////These values need to be checked//
            	yCommand = driveJoystick.getAxis(Joystick.AxisType.kY); ////to see if they're what we want///
            	rotationCommand = driveJoystick.getTwist();
            }
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
                    ySmooth = 0;
                }
                if (xCommand < P && xCommand > (-P)) {
                    xSmooth = 0;
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
                    rotationSmooth = ((1 / (1 - P)) * yCommand + (1 - (1 / (1 - P))));
                }
                // If the rotation is negative and passed the deadzone (joystick is twisted counterclockwise
                if (yCommand <= rotationP) {
                    rotationSmooth = ((1 / (1 - P)) * yCommand - (1 - (1 / (1 - P))));
                }

                // Drive using util
                moveDriveTrain(xSmooth, ySmooth, rotationSmooth, gyro.getAngle());
            }
            driveToDashboard();
            Timer.delay(.1); //10ms delay
        }
    }

	public synchronized void setJoystickStateCommand(boolean joystickStateCommand) {
		this.joystickStateCommand = joystickStateCommand;
	}

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
	}


}