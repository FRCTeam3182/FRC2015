
/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 							Lifter
 *  This thread controls the lifter mechanisms and associated 
 *  components
 * 
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.*;   

public class Lifter implements Runnable {

	//declare instances

	private Joystick lifterJoystick;
<<<<<<< HEAD

	private PIDController pidController;

=======
>>>>>>> origin/master

	private Talon motor;

	private DigitalInput limitSwitch;
	
	
	
	
	public Lifter(){
		
		motor = new Talon(4); //TODO check port for new motor

		limitSwitch = new DigitalInput(13);

		lifterJoystick = new Joystick(1);
<<<<<<< HEAD

		pidController = new PIDController(0.45, 0, 0, encoders.get(0), talons.get(0));

=======
>>>>>>> origin/master
	}
	


	public void run(){
		while (true) 
			moveLifter(lifterJoystick.getAxis(Joystick.AxisType.kY));
		
	}

	/**
	 Speed is -1 to 1
	 */
	public void moveLifter(double speed){
		if(Math.abs(speed)>.3)
			motor.set(speed);			
		else
			motor.set(0);			
	}
	public synchronized void move(double speed)
	{
		moveLifter(speed);
	}
	public void moveLifterRaw(double speed) 
	{
		motor.set(speed);
	}

	public void resetLifter(){
		while (!limitSwitch.get())
			motor.set(-0.3);		
		return;
	}

	public synchronized void reset() {
		resetLifter();
	}

	public Joystick getJoystick() {
		return lifterJoystick;
	}
}

