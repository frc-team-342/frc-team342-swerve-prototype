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

	private Drivesystem() {

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
		// TODO Auto-generated guber method stub

	}

	public static Drivesystem getInstance() {
		return instance;
	}

	public void polar(double angle, double maginitude) {
		// TODO: FIX THIS ITS BROKEN
	}

	public void AngleAdjust(int number, double speed) {
		CANTalon Talon = null;
		if (number == 1) {
			Talon = FRTurn;
		}
		if (number == 2) {
			Talon = BRTurn;
		}
		if (number == 3) {
			Talon = BLTurn;
		}
		if (number == 4) {
			Talon = FLTurn;
		}
		if (Talon != null && Talon.getControlMode() == CANTalon.TalonControlMode.Voltage) {
		  //Talon.setControlMode(CANTalon.TalonControlMode.Voltage.value);
			Talon.set(speed);
		}
	}

	public void DriveWheels(double speed) {
/*		if (FRDrive.getControlMode() == BRDrive.getControlMode() && 
			FRDrive.getControlMode() == FLDrive.getControlMode() &&
		    FRDrive.getControlMode() == BLDrive.getControlMode()) {
		}// do FRDrive.getCont... == CANTalon.Talon...Mode.Voltage 
		FRDrive.set(speed);
		BRDrive.set(speed);
		FLDrive.set(speed);
		FRDrive.set(speed);
	*/
		
	}
	

}

