package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.ArduinoLights;
import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.lights.LightsEnum;

public class UltimateBeautyPoss implements AutoPossibilityInterface {

    @Override
    public String getName() {
        return "Gather all Totes Possibility";
    }

    @Override
    public void executePossibility(Lifter lifter, DriveTrain driverTrain) { //TODO Change to correct values
        DriveTrain dt = Robot3182.getDriveTrain();
        Lifter l = Robot3182.getLifter();
        l.resetLifter();
        dt.moveDriveTrainDistance(0, 2); // Move drive train to tote
        l.setLifter(5); // Pick up first tote
        dt.moveDriveTrainDistance(0, -3); // Move backwards
        dt.moveDriveTrainDistance(-5, 0); // Move to next tote
        l.setLifter(4); // Set first tote on top of second tote
        dt.moveDriveTrainDistance(0, -1); // Move backwards for room
        l.resetLifter(); // Reset the lifter
        dt.moveDriveTrainDistance(0, 1); // Move forwards again
        l.setLifter(5); // Pick up the 2 totes
        dt.moveDriveTrainDistance(0, -3); // Move backwards
        dt.moveDriveTrainDistance(-5, 0); // Move to 3rd tote
        l.resetLifter(); // Reset the lifter
        dt.moveDriveTrainDistance(0, 3); // Move back towards 3rd tote
        l.setLifter(8); // Set totes on top of 3rd tote
        dt.moveDriveTrainDistance(0, -1); // Back up a bit
        l.resetLifter(); // Reset the lifter
        dt.moveDriveTrainDistance(0, 1); // Move back towards 3 totes
        l.setLifter(9); // Pick up all 3 totes
        dt.moveDriveTrainDistance(0, 10); // Move towards auto zone
    }

    @Override
    public void sendLightsCommand(ArduinoLights arduinoLights) {
        arduinoLights.setLightSequence(LightsEnum.ULTIMATE_POSS);
    }
}
