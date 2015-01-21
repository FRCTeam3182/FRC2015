package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class Sensors extends Object implements Runnable {

	private BuiltInAccelerometer accel;
    private AnalogInput ultrasonic;
    private Encoder rightDriveEncoder;
    private Encoder leftDriveEncoder;
    double leftVoltage;
    double rightVoltage;
    volatile int shootDistance;
    

    public Sensors() {
        //initializing everything
        rightDriveEncoder = new Encoder(4, 3);
        leftDriveEncoder = new Encoder(2, 1);
        ultrasonic = new AnalogInput(1);
        rightDriveEncoder.setDistancePerPulse(.08168);
        accel = new BuiltInAccelerometer();
    }

    public void run() {
        while (true) {
            
            SmartDashboard.putNumber("Distance", ultrasonic.getVoltage());
            SmartDashboard.putNumber("Speed Right", rightDriveEncoder.getRate());
            SmartDashboard.putNumber("Speed Left", leftDriveEncoder.getRate());
            Timer.delay(.1);
            
            //If voltage is between x and y, we're in the right position
            if (leftVoltage >= .598 && leftVoltage <= .897) {
            shootDistance = 1; 
            //If voltage is between x and y, we're too far
            } else if (leftVoltage >= .898) {
            shootDistance = 2; 
            //If voltage is between x and y, we;re too close
            } else if (leftVoltage >= .001 && leftVoltage <= .597) {
            shootDistance = 0; 
            }
            SmartDashboard.putNumber("shootDistance Variable", shootDistance);
        }
    }
    
    public synchronized int shootingDistance() {
        return shootDistance;
    }
}
