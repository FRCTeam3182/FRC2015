/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 							Lifter
 *  This thread controls the lifter mechanisms and associated 
 *  components
 * 
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.*;

import java.util.ArrayList;

public class Lifter implements Runnable {

	//declare instances

	private Joystick lifterJoystick;

	private PIDController pidController;


	private ArrayList<Talon> talons = new ArrayList<Talon>();
	private ArrayList<Encoder> encoders = new ArrayList<Encoder>();

	private ArrayList<Ultrasonic> ultrasonics = new ArrayList<Ultrasonic>();

	private DigitalInput limitSwitch;
	
	public Lifter(){

		talons.add(new Talon(4));
		talons.add(new Talon(5));

		encoders.add(new Encoder(9,10));
		encoders.add(new Encoder(11,12));

		ultrasonics.add(new Ultrasonic(14, 15));
		ultrasonics.add(new Ultrasonic(16, 17));

		limitSwitch = new DigitalInput(13);

		lifterJoystick = new Joystick(1);

		pidController = new PIDController(0.45, 0, 0, encoders.get(0), talons.get(0));

	}
	
	public void run(){
		moveLifter(lifterJoystick.getAxis(Joystick.AxisType.kY));
	}

	/**
	 Speed is -1 to 1
	 */
	public void moveLifter(double speed){
		if (encoders.get(0).getDistance() == 0 && speed < 0){
			return;
		}
		else if (encoders.get(0).getDistance() == 100 && speed > 0){ // TODO Change 100 to actual limit
			return;
		}

		talons.get(0).set(speed);
		talons.get(1).set(speed * -1); // TODO See which Talon needs to be reversed

	}

	/**
	 * Height is in inches (in)
	 * @param heightIn
	 */
	public void setLifter(int heightIn){
		int convertedHeight = heightIn * 2; // TODO Set real conversion rate

		if (convertedHeight < 0 || convertedHeight > 100){ // TODO Change the max height
			return;
		}

		while (encoders.get(0).getDistance() != convertedHeight){
			if (convertedHeight < 0){
				talons.get(0).set(-0.5);
				talons.get(1).set(0.5);
			}
			else{
				talons.get(0).set(0.5);
				talons.get(1).set(-0.5);
			}

		}
	}
	
	/*
	 * Uses the ultrasonic sensors to drive the robot to line up properly with the totes
	 */
	public void getInPosition(){
		
		//check if the lifter is in the correct place
		
		//
	
	}


	public void resetLifter(){
		while (!limitSwitch.get()){ // TODO See which limit switch is at bottom and if .get() returns true when down
			talons.get(0).set(0.3);
			talons.get(1).set(-0.3); // TODO See which Talon needs to be reversed
		}

	}

	public synchronized void reset() {
		resetLifter();
	}
}
