package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3182.robot.lights.LightsEnum;

import java.util.ArrayList;

public class UltrasonicSensor implements Runnable {

    ArduinoLights arduinoLights;

    private ArrayList<AnalogInput> sensors = new ArrayList<AnalogInput>();


    public UltrasonicSensor(ArduinoLights arduinoLights) {
        this.arduinoLights = arduinoLights;
        sensors.add(new AnalogInput(1));
        sensors.add(new AnalogInput(2));
    }


    @Override
    public void run() {
        while (true) {
            // Green one is 1V/1024 per centimeter
            // Black one is 1V/1024 per 5 millimeters

            double blackVoltage = sensors.get(0).getValue(); // Black one
            // double greenVoltage = sensors.get(1).getValue(); // Green one

            double blackDistance = (blackVoltage * 5) * 0.0393701; //inches
            //double greenDistance = (greenVoltage / 1024) / 10;

            SmartDashboard.putNumber("Ultrasonic 1", blackVoltage);
            //SmartDashboard.putNumber("Ultrasonic 2", greenVoltage);

            SmartDashboard.putNumber("Ultrasonic 1 Distance", blackDistance);
            //SmartDashboard.putNumber("Ultrasonic 2 Distance", greenDistance);

            if (blackDistance < 30) {
                arduinoLights.setLightSequence(LightsEnum.TOP_LED.setData(10 + ((blackDistance - 255) * (30 - 10)) / (0 - 255) +
                        "," + (10 + ((blackDistance - 0) * (30 - 10)) / (255 - 0)) +
                        "," + 0));
            }
        }
    }
}
