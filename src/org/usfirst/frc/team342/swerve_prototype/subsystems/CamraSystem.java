package org.usfirst.frc.team342.swerve_prototype.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CamraSystem extends Subsystem {
 
	private static final CamraSystem instance = new CamraSystem();
	
	private CamraSystem(){
		
	}
	public static CamraSystem getInstance(){
		return instance;
		
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//guber
	}
}

