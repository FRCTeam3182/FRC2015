package org.usfirst.frc.team3182.robot.test.motorTest;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

import java.util.ArrayList;
import java.util.List;

public class DriveTrainThread implements Runnable {

    private Joystick joystick;

    private List<Talon> talons = new ArrayList<Talon>();

    public DriveTrainThread(Joystick joystick) {
        this.joystick = joystick;

        talons.add(0, new Talon(1)); //TODO Check these ports
        talons.add(1, new Talon(2));
        talons.add(2, new Talon(3));
        talons.add(3, new Talon(4));
    }

    @Override
    public void run() {
        if (joystick.getRawButton(1)){
            talons.get(0).set(0.5);
        }
        else if (joystick.getRawButton(2)){
            talons.get(0).set(-0.5);
        }
        else if (joystick.getRawButton(3)){
            talons.get(1).set(0.5);
        }
        else if (joystick.getRawButton(4)){
            talons.get(1).set(-0.5);
        }
        else if (joystick.getRawButton(5)){
            talons.get(2).set(0.5);
        }
        else if (joystick.getRawButton(6)){
            talons.get(2).set(-0.5);
        }
        else if (joystick.getRawButton(7)){
            talons.get(3).set(0.5);
        }
        else if (joystick.getRawButton(8)){
            talons.get(3).set(-0.5);
        }
    }
}
