package org.usfirst.frc.team3182.robot.auto;

import org.usfirst.frc.team3182.robot.ArduinoLights;
import org.usfirst.frc.team3182.robot.DriveTrain;
import org.usfirst.frc.team3182.robot.Lifter;
import org.usfirst.frc.team3182.robot.Robot3182;
import org.usfirst.frc.team3182.robot.lights.LightsEnum;

import edu.wpi.first.wpilibj.Timer;

public class PushTotePoss implements AutoPossibilityInterface{

    @Override
    public String getName() {
        return "Push tote into auto possibility";
    }

    @Override
    public void executePossibility(Lifter lifter, DriveTrain driverTrain) {
        DriveTrain dt = Robot3182.getDriveTrain();
        //Lifter l = Robot3182.getLifter();
        dt.moveDriveTrain(0,.8,0);
        //l.resetLifter();
        //l.setLifter(5); //TODO Set correct height for tote
        //dt.moveDriveTrainDistance(0, 10);
        //l.resetLifter();
        Timer.delay(2.4);
        dt.moveDriveTrain(0, 0, 0);

    }

    @Override
    public void sendLightsCommand(ArduinoLights arduinoLights) {
        arduinoLights.setLightSequence(LightsEnum.PUSH_TOTE_POSS);
    }
}
