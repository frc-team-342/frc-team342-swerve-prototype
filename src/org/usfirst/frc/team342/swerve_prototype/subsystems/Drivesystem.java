package org.usfirst.frc.team342.swerve_prototype.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivesystem extends Subsystem {
	
	private static final Drivesystem instance = new Drivesystem();
	
	private Drivesystem(){
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
public static Drivesystem getInstance(){
	return instance;
}
public void polar (double angle, double maginitude ){
	//TODO: FIX THIS ITS BROKEN
}
}
