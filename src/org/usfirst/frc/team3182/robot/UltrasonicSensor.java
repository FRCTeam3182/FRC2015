package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

public class UltrasonicSensor implements Runnable{

    private ArrayList<AnalogInput> sensors = new ArrayList<AnalogInput>();


    public UltrasonicSensor(){
        sensors.add(new AnalogInput(1));
        sensors.add(new AnalogInput(2));
    }




    @Override
    public void run() {
        // Green one is 1V/1024 per centimeter
        // Black one is 1V/1024 per 5 millimeters

        double blackVoltage = sensors.get(0).getVoltage(); // Black one
        double greenVoltage = sensors.get(1).getVoltage(); // Green one

        double blackDistance = (blackVoltage / 1024) / 5;
        double greenDistance = (greenVoltage / 1024) / 10;

        SmartDashboard.putNumber("Ultrasonic 1", blackDistance);
        SmartDashboard.putNumber("Ultrasonic 2", greenDistance);
    }
}
