
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

	private ArrayList<Talon> talons = new ArrayList<Talon>();
	private ArrayList<Encoder> encoders = new ArrayList<Encoder>();

	private DigitalInput limitSwitch;
	
	private PIDController controller;
//	private PIDController m1control;
//	private PIDController m2control;
	
	
	
	public Lifter(){
		
		talons.add(new Talon(4));         //Used w/ user input
		talons.add(new Talon(5));         //Hopefully controlled by PID

		encoders.add(new Encoder(9,10));  //Talon 4
		encoders.add(new Encoder(11,12)); //Talon 5



		limitSwitch = new DigitalInput(13);

		lifterJoystick = new Joystick(1);
		
		controller = new PIDController(1.0, 0.1, 0.0, encoders.get(1), talons.get(1));     //instantiate PIDController
																						   // TODO: tune
		//m1controller = new PIDController(1.0, 0.1, 0.0, encoders.get(0), talons.get(0));
		//m2controller = new PIDController(1.0, 0.1, 0.0, encoders.get(1), talons.get(1));
		//if you want double PID control
		controller.setAbsoluteTolerance(1);
		controller.setContinuous(true);
	}
	

	
	public void run(){
		while (true) {
		moveLifterRaw(lifterJoystick.getAxis(Joystick.AxisType.kY));
		//controller.enable();
		//controller.setSetpoint(encoders.get(0).get()); //TODO: offset?
		//m1controller.enable();
		//m1controller.setSetpoint(lifterJoystick.getAxis(Joystick.AxisType.kY));
		//m2controller.enable();
		//m2controller.setSetpoint(lifterJoystick.getAxis(Joystick.AxisType.kY));
		}
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
		//talons.get(1).set(speed * -1); 

	}
	public void moveLifterRaw(double speed) //also use if you want double PID control
	{
		//controller.disable();
		if(Math.abs(speed)>.3){
		talons.get(0).set(speed*.9);
		talons.get(1).set(speed); // maybe See which Talon needs to be reversed
		}
		else{
		talons.get(0).set(0);
		talons.get(1).set(0);
		}
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
		//		talons.get(1).set(0.5);
			}
			else{
				talons.get(0).set(0.5);
	//			talons.get(1).set(-0.5);
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
		//	talons.get(1).set(-0.3);
		}

	}

	public synchronized void reset() {
		//resetLifter();
	}
	public synchronized void moveMotor(int motor, double s){
		if(Math.abs(s)>.3)talons.get(motor).set(s);
		else{
			talons.get(motor).set(0);
			};
	}

}

