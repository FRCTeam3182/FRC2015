package org.usfirst.frc.team3182.robot.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

import java.util.ArrayList;

public class DriverUtil {

    private final RobotDrive drive;
    private final ArrayList<Encoder> encoders = new ArrayList<Encoder>(4);
    
    /* 
     * Encoder location on robot
     *  0 --^-- 2
     *    |   |
     *  1 ----- 3
     * 
     *  Key:
     *  # = arraylist location
     *  ^ = front of robot (lifter)
     */
    
    public DriverUtil(){
        drive = new RobotDrive(1, 2, 3, 4);
        drive.setSafetyEnabled(false);
        
        encoders.set(0, new Encoder(1,2));
        encoders.set(0, new Encoder(1,2));
    }

    public void moveDriveTrain(double x, double y, double rotation){
        drive.mecanumDrive_Cartesian(x, y, rotation, 0);
    }

    public void moveDriveTrain(double x, double y, double rotation, double gyro){
        drive.mecanumDrive_Cartesian(x, y, rotation, gyro);
    }

    public void moveDriveTrainDistance(double distX, double distY, double angleRotation){
     //Moves the robot in an arbitrary distance unit, determined by the encoder's output
    	
    }
}
