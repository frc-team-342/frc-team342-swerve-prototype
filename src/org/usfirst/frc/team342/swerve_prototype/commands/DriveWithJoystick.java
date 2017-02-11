package org.usfirst.frc.team342.swerve_prototype.commands;

import org.usfirst.frc.team342.swerve_prototype.OI;
import org.usfirst.frc.team342.swerve_prototype.subsystems.Drivesystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithJoystick extends Command {

	private static final double DEAD_ZONE = 0.3;

	private Drivesystem drive;
	private Joystick joystick;
	
	public DriveWithJoystick (){
		drive = Drivesystem.getInstance();
		joystick = OI.stick;
		
		requires(drive);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double angle = (Math.abs(joystick.getDirectionDegrees() + 360) % 360) / 360;
		double magnitude = joystick.getMagnitude();
		double rotation = joystick.getRawAxis(4);

		if (Math.abs(magnitude) < DEAD_ZONE) {
			magnitude = 0;             
		}

		if (magnitude > 1.0 ){
			magnitude = 1.0;
		}
		
		
		drive.DWJmanup(angle, magnitude, rotation, false);
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		drive.StopDrive();
		drive.StopTurn();
	}

}
