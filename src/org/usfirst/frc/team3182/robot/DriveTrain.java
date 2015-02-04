/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  
 *                          The DriveTrain								*
 * 																		*
 * This class is the thread for the drivetrain without shifters			*
 * 																		*
 * 																		*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/

package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3182.robot.util.DriverUtil;

public class DriveTrain implements Runnable {


	private final DriverStation driverStation;

	// Direct motor commands (start at unmoving)
//	private double rightFrontMotorCommand = 0;
//	private double rightBackMotorCommand = 0;
//	private double leftFrontMotorCommand = 0;
//	private double leftBackMotorCommand = 0;

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

    private static DriverUtil du;

	public DriveTrain() {

		// Initializing everything
		driverStation = DriverStation.getInstance();

		// Drivetrain


		// Joystick
		driveJoystick = new Joystick(1);

        du = new DriverUtil();
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

                // Drive ///////////needs solidifying//////////
                // First possibility
               //

                // Second possibility
                //    WRITE CUSTOM ARCADE MECANUM CODE HERE
            }
            driveToDashboard();
            Timer.delay(.1); //10ms delay
        }
    }

	public synchronized void setJoystickStateCommand(boolean joystickStateCommand) {
		this.joystickStateCommand = joystickStateCommand;
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

    public static synchronized DriverUtil getDriverUtil() {
        return du;
    }
}