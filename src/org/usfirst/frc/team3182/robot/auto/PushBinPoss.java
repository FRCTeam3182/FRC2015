package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.Sensors;

public class PushBinPoss implements AutoPossibilityInterface
{
    @Override
    public String getName() {
        return "Push Bin into Auto Possibility";
    }

    @Override
    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain) {
        DriveTrain dt = Robot3182.getDriveTrain();
        Lifter l = Robot3182.getLifter();
        dt.moveDriveTrainDistance(0, 2);
        l.resetLifter();
        l.setLifter(7); //TODO Change to correct measurements
        dt.moveDriveTrainDistance(0, 10);
        l.resetLifter();
    }
}
