package org.usfirst.frc.team342.swerve_prototype.subsystems;

import org.usfirst.frc.team342.swerve_prototype.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivesystem extends Subsystem {
	
	private static final Drivesystem instance = new Drivesystem();
	
	private CANTalon FRDrive;
	private CANTalon FRTurn;
	private CANTalon FLDrive;
	private CANTalon FLTurn;
	private CANTalon BRDrive;
	private CANTalon BRTurn;
	private CANTalon BLDrive;
	private CANTalon BLTurn;
	
	private Drivesystem(){
		
		FRDrive = new CANTalon(RobotMap.frdrive);
		FRTurn = new CANTalon(RobotMap.frturn);
		FLDrive = new CANTalon(RobotMap.frdrive);
		FLTurn = new CANTalon(RobotMap.flturn);
		BRDrive = new CANTalon(RobotMap.brdrive);
		BRTurn = new CANTalon(RobotMap.brturn);
		BLDrive = new CANTalon(RobotMap.bldrive);
		BLTurn = new CANTalon(RobotMap.blturn);
		
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
