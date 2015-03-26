package org.usfirst.frc.team3182.robot.test.motorTest;

import edu.wpi.first.wpilibj.Joystick;

public class MotorTest {

    private Joystick driveJoystick;
    private Joystick lifterJoystick;

    public MotorTest(Joystick driveJoystick, Joystick lifterJoystick) {
        this.driveJoystick = driveJoystick;
        this.lifterJoystick = lifterJoystick;
    }

    public void initiateTest(){
        DriveTrainThread driveTrainVar = new DriveTrainThread(driveJoystick);
        new Thread(driveTrainVar, "DriveTrainTest").start();
        LifterThread lifterThreadVar = new LifterThread(lifterJoystick);
        new Thread(lifterThreadVar, "LifterTest").start();
    }

    public void killThreads(){

    }
}
