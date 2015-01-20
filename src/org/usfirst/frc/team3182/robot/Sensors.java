package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class Sensors extends Object implements Runnable {

	private BuiltInAccelerometer accel;
    private AnalogInput leftRangeFinder;
    private AnalogInput rightRangeFinder;
    private Encoder rightDriveEncoder;
    private Encoder leftDriveEncoder;
    double leftVoltage;
    double rightVoltage;
    volatile int shootDistance;
    

    public Sensors() {
        //initializing everything
        rightDriveEncoder = new Encoder(4, 3);
        leftDriveEncoder = new Encoder(2, 1);
        leftRangeFinder = new AnalogInput(1);
        rightRangeFinder = new AnalogInput(2);
        rightDriveEncoder.setDistancePerPulse(.08168);
        accel = new BuiltInAccelerometer();
    }

    public void run() {
        while (true) {
            leftVoltage = leftRangeFinder.getVoltage();
            rightVoltage = rightRangeFinder.getVoltage();
            
            SmartDashboard.putNumber("Average Voltage left", leftVoltage);
            SmartDashboard.putNumber("Average Voltage right", rightVoltage);
            SmartDashboard.putNumber("Speed Right", rightDriveEncoder.getRate());
            SmartDashboard.putNumber("Speed Left", leftDriveEncoder.getRate());
            Timer.delay(.1);
             
//            if (Math.abs(leftVoltage-rightVoltage) > .3) {
//                leftVoltage = 0;
//                rightVoltage = 0;
//            }
            
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
