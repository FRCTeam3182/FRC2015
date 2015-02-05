/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 							Lifter
 *  This thread controls the lifter mechanisms and associated 
 *  components
 * 
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/
package org.usfirst.frc.team3182.robot;


import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import org.usfirst.frc.team3182.robot.util.LifterUtil;
import edu.wpi.first.wpilibj.Joystick;
public class Lifter implements Runnable {

	//declare instances
	private Sensors sensors;
	private Joystick lifterJoystick;
	
	private static LifterUtil lu;

	public Lifter(Sensors sensors){
		
		lu = new LifterUtil();
		lifterJoystick = new Joystick(2);
		this.sensors = sensors;
	
	}
	
	public void run(){
		lu.moveLifter(lifterJoystick.getAxis(Joystick.AxisType.kY));
		
	}

	public static synchronized void reset() {
		lu.resetLifter();
	}

	public static synchronized LifterUtil getLifterUtil() {
		return lu;
	}
}
