package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.Sensors;
import org.usfirst.frc.team3182.robot.util.DriverUtil;

public class DriveForwardPoss implements AutoPossibilityInterface {


    @Override
    public String getName() {
        return "Driver Forward Possibility";
    }

    @Override
    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain) {
        DriverUtil du = DriveTrain.getDriverUtil();
        du.moveDriveTrainDistance(0, 5);
    }
}
