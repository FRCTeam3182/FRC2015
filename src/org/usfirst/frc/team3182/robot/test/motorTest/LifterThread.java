package org.usfirst.frc.team3182.robot.test.motorTest;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class LifterThread implements Runnable{

    private Joystick joystick;

    private Talon motor;

    public LifterThread(Joystick joystick) {
        this.joystick = joystick;

        motor = new Talon(10); //TODO Change to correct port
    }

    @Override
    public void run() {
        if (joystick.getRawButton(1)){
            motor.set(0.5);
        }
        else if (joystick.getRawButton(2)){
            motor.set(-0.5);
        }
    }
}
