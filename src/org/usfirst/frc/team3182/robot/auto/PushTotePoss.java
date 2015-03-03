package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;

public class PushTotePoss implements AutoPossibilityInterface{

    @Override
    public String getName() {
        return "Push tote into auto possibility";
    }

    @Override
    public void executePossibility(Lifter lifter, DriveTrain driverTrain) {
        DriveTrain dt = Robot3182.getDriveTrain();
        Lifter l = Robot3182.getLifter();
        dt.moveDriveTrainDistance(0, 2);
        l.resetLifter();
        l.setLifter(5); //TODO Set correct height for tote
        dt.moveDriveTrainDistance(0, 10);
        l.resetLifter();

    }
}
