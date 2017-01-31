package org.usfirst.frc.team342.swerve_prototype.subsystems;

import org.usfirst.frc.team342.swerve_prototype.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CamraSystem extends Subsystem {
 
	private static final CamraSystem instance = new CamraSystem();
	private Servo Servo1;
	private Servo Servo2;
	
	private CamraSystem(){
		Servo1 = new Servo (RobotMap.Servo1);
		Servo2 = new Servo (RobotMap.Servo2);
	}
	public static CamraSystem getInstance(){
		return instance;
		
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	public void CameraAim (double Rotation, double Tilt){
		Rotation = 0.5 * Rotation + 0.5;
		Tilt = 0.5 * Tilt + 0.5;
		Servo1.setPosition(Rotation);
		Servo2.setPosition(Tilt);
		
	}
}

