package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Sensors;

public interface AutoPossibilityInterface {

    public String getName();

    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain);
}
