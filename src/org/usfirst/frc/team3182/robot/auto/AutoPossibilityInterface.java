package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.ArduinoLights;
import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;

public interface AutoPossibilityInterface {

    public String getName();

    public void executePossibility(Lifter lifter, DriveTrain driverTrain);

    public void sendLightsCommand(ArduinoLights arduinoLights);
}
