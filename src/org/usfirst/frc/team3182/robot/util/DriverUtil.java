package org.usfirst.frc.team3182.robot.util;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

import java.util.ArrayList;

public class DriverUtil {

    private final RobotDrive drive;
    private ArrayList<Encoder> encoders = new ArrayList<Encoder>(4);
    
    /* 
     * Encoder location on robot
     *  0 --^-- 2
     *    |   |
     *  1 ----- 3
     * 
     *  Key:
     *  Number = arraylist location
     *  ^ = front of robot (lifter)
     */
    
    public DriverUtil(){
        drive = new RobotDrive(1, 2, 3, 4);
        drive.setSafetyEnabled(false);
        
        encoders.set(0, new Encoder(1,2));
        encoders.set(1, new Encoder(3,4));
        encoders.set(2, new Encoder(5,6));
        encoders.set(3, new Encoder(7,8));

    }

    public void moveDriveTrain(double x, double y, double rotation){
        drive.mecanumDrive_Cartesian(x, y, rotation, 0);
    }

    public void moveDriveTrain(double x, double y, double rotation, double gyro){
        drive.mecanumDrive_Cartesian(x, y, rotation, gyro);
    }

    public void moveDriveTrainDistance(double distX, double distY)
    {
     //Moves the robot in an arbitrary distance unit, determined by the encoder's output
    	//double oldDistance = 0.00;
        distX = distY * 1;
        distY = distY * 1; // TODO Change to correct values
        double xyRatio = distX/(distY+distX);
        double yxRatio = distY/(distY+distX);
        double totDistance = Math.sqrt(distX * distX + distY * distY); //pythagorean theorem
        double encoderXTotDist = 0;
        double encoderYTotDist = 0;
        for(Encoder e : encoders)
            e.reset();
        moveDriveTrain(xyRatio, yxRatio, 0, 0);
        do
        {
            encoderXTotDist -= encoders.get(0).getDistance() / 4;
            encoderXTotDist += encoders.get(2).getDistance() / 4;
            encoderXTotDist += encoders.get(1).getDistance() / 4;
            encoderXTotDist -= encoders.get(3).getDistance() / 4;
            encoderYTotDist += encoders.get(0).getDistance() / 4;
            encoderYTotDist += encoders.get(2).getDistance() / 4;
            encoderYTotDist += encoders.get(1).getDistance() / 4;
            encoderYTotDist += encoders.get(3).getDistance() / 4;

        }while(Math.sqrt(Math.pow(encoderXTotDist, 2) + Math.pow(encoderYTotDist, 2))<totDistance);

        moveDriveTrain(0, 0, 0, 0);
    }

}

