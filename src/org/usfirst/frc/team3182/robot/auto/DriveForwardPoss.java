package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.ArduinoLights;
import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.lights.LightsEnum;

import edu.wpi.first.wpilibj.Timer;

public class DriveForwardPoss implements AutoPossibilityInterface {


    @Override
    public String getName() {
        return "Driver Forward Possibility";
    }

    @Override
    public void executePossibility(Lifter lifter, DriveTrain driverTrain) {

        DriveTrain dt = Robot3182.getDriveTrain();
        dt.moveDriveTrain(0,.6,0);
        Timer.delay(3);
        dt.moveDriveTrain(0,0,0);
    }

    @Override
    public void sendLightsCommand(ArduinoLights arduinoLights) {
        arduinoLights.setLightSequence(LightsEnum.DRIVE_FORWARD_POSS);
    }
}
