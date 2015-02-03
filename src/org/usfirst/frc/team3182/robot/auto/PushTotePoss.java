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
        return "Push into Tote Possibility";
    }

    @Override
    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain) {
        DriverUtil du = Robot3182.getDriverUtil();
        LifterUtil lu = Robot3182.getLifterUtil();
        du.moveDriveTrainDistance(0, 2);
        lu.moveLifter(0.5);
        du.moveDriveTrainDistance(0, 10);
        lu.resetLifter();

    }
}
