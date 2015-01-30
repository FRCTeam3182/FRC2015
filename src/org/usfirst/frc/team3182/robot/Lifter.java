/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 							Lifter
 *  This thread controls the lifter mechanisms and associated 
 *  components
 * 
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/
package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;

public class Lifter implements Runnable {

	//declare instances
	private Sensors sensors;
	private Talon rightMotor;
	private Talon leftMotor;
	private Relay lock;
	
	public Lifter(Sensors sensors){
		
		rightMotor = new Talon(3);
		leftMotor = new Talon(4);
		this.sensors = sensors;
	}
	
	public void run(){
		
	}
}
