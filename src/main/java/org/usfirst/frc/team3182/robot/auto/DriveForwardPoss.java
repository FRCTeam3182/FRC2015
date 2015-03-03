package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;

public class DriveForwardPoss implements AutoPossibilityInterface {


    @Override
    public String getName() {
        return "Driver Forward Possibility";
    }

    @Override
    public void executePossibility(Lifter lifter, DriveTrain driverTrain) {
        DriveTrain dt = Robot3182.getDriveTrain();
        dt.moveDriveTrainDistance(0, 5);
    }
}
