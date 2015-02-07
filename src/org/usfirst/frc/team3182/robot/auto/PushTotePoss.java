package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.Sensors;
import org.usfirst.frc.team3182.robot.util.LifterUtil;

public class PushTotePoss implements AutoPossibilityInterface{

    @Override
    public String getName() {
        return "Push tote into auto possibility";
    }

    @Override
    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain) {
        DriveTrain dt = Robot3182.getDriveTrain();
        LifterUtil lu = Lifter.getLifterUtil();
        dt.moveDriveTrainDistance(0, 2);
        lu.resetLifter();
        lu.setLifter(5); //TODO Set correct height for tote
        dt.moveDriveTrainDistance(0, 10);
        lu.resetLifter();

    }
}
