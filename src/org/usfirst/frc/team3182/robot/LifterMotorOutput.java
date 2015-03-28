package org.usfirst.frc.team3182.robot;

import edu.wpi.first.wpilibj.SpeedController;

public class LifterMotorOutput implements SpeedController {

	private double speed;
	
	@Override
	public void pidWrite(double output) {
		speed = output;
		
	}

	@Override
	public double get() {
		return speed;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		this.speed = speed;
	}

	@Override
	public void set(double speed) {
		this.speed = speed;
	}

	@Override
	public void disable() {
		speed = 0;
	}
}
