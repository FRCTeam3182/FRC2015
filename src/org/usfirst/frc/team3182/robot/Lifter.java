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
import org.usfirst.frc.team3182.robot.util.LifterUtil;

public class Lifter implements Runnable {

	//declare instances
	private Sensors sensors;
	private Talon rightMotor;
	private Talon leftMotor;
	private Relay lock;

	private static LifterUtil lu;

	public Lifter(Sensors sensors){
		lu = new LifterUtil();
		rightMotor = new Talon(3);
		leftMotor = new Talon(4);
		this.sensors = sensors;
	}
	
	public void run(){
		
	}

	public static synchronized void reset() {
		lu.resetLifter();
	}

	public static synchronized LifterUtil getLifterUtil() {
		return lu;
	}
}
