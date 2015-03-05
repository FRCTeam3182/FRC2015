package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.ArduinoLights;
import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.lights.LightsEnum;

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

    @Override
    public void sendLightsCommand(ArduinoLights arduinoLights) {
        arduinoLights.setLightSequence(LightsEnum.DRIVE_FORWARD_POSS);
    }
}
