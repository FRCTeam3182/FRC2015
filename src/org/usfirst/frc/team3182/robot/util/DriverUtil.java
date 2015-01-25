package org.usfirst.frc.team3182.robot.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

import java.util.ArrayList;

public class DriverUtil {

    private final RobotDrive drive;
    private final ArrayList<Encoder> encoders = new ArrayList<Encoder>();

    public DriverUtil(){
        drive = new RobotDrive(1, 2, 3, 4);
        drive.setSafetyEnabled(false);
    }

    public void moveDriveTrain(double x, double y, double rotation){
        drive.mecanumDrive_Cartesian(x, y, rotation, 0);
    }

    public void moveDriveTrain(double x, double y, double rotation, double gyro){
        drive.mecanumDrive_Cartesian(x, y, rotation, gyro);
    }

    public void moveDriveTrainRotation(double rotationX, double rotationY, double rotationOfBot){
           
    }


}
