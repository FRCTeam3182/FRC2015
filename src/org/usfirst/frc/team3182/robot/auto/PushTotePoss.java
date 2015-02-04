package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.Sensors;
import org.usfirst.frc.team3182.robot.util.DriverUtil;
import org.usfirst.frc.team3182.robot.util.LifterUtil;

public class PushTotePoss implements AutoPossibilityInterface{

    @Override
    public String getName() {
        return "Push tote into auto possibility";
    }

    @Override
    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain) {
        DriverUtil du = DriveTrain.getDriverUtil();
        LifterUtil lu = Lifter.getLifterUtil();
        du.moveDriveTrainDistance(0, 2);
        lu.resetLifter();
        lu.setLifter(5); //TODO Set correct height for tote
        du.moveDriveTrainDistance(0, 10);
        lu.resetLifter();

    }
}
