package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.Sensors;
import org.usfirst.frc.team3182.robot.util.DriverUtil;
import org.usfirst.frc.team3182.robot.util.LifterUtil;

public class UltimateBeautyPoss implements AutoPossibilityInterface {

    @Override
    public String getName() {
        return "Gather all Totes Possibility";
    }

    @Override
    public void executePossibility(Sensors sensors, Lifter lifter, DriveTrain driverTrain) { //TODO Change to correct values
        DriverUtil du = DriveTrain.getDriverUtil();
        LifterUtil lu = Lifter.getLifterUtil();
        lu.resetLifter();
        du.moveDriveTrainDistance(0, 2); // Move drive train to tote
        lu.setLifter(5); // Pick up first tote
        du.moveDriveTrainDistance(0, -3); // Move backwards
        du.moveDriveTrainDistance(-5, 0); // Move to next tote
        lu.setLifter(4); // Set first tote on top of second tote
        du.moveDriveTrainDistance(0, -1); // Move backwards for room
        lu.resetLifter(); // Reset the lifter
        du.moveDriveTrainDistance(0, 1); // Move forwards again
        lu.setLifter(5); // Pick up the 2 totes
        du.moveDriveTrainDistance(0, -3); // Move backwards
        du.moveDriveTrainDistance(-5, 0); // Move to 3rd tote
        lu.resetLifter(); // Reset the lifter
        du.moveDriveTrainDistance(0, 3); // Move back towards 3rd tote
        lu.setLifter(8); // Set totes on top of 3rd tote
        du.moveDriveTrainDistance(0, -1); // Back up a bit
        lu.resetLifter(); // Reset the lifter
        du.moveDriveTrainDistance(0, 1); // Move back towards 3 totes
        lu.setLifter(9); // Pick up all 3 totes
        du.moveDriveTrainDistance(0, 10); // Move towards auto zone
    }
}
