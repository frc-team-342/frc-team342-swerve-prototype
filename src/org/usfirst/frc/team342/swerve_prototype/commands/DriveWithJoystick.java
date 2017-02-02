package org.usfirst.frc.team342.swerve_prototype.commands;

import org.usfirst.frc.team342.swerve_prototype.RobotMap;
import org.usfirst.frc.team342.swerve_prototype.subsystems.Drivesystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
private static final int JOY_STICK = Joystick.AxisType.kY.value;

private static final double DEAD_ZONE = 0.3;

private static final int JOY_STICK1 = 0;

private Drivesystem drive;
	private Joystick joystick;
	
	public DriveWithJoystick (){
		drive = Drivesystem.getInstance();
		joystick = new Joystick(RobotMap.JoystickPort);
		

		requires(drive);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double angle = (joystick.getDirectionDegrees()/360.0) + 0.5;
		double magnitude = joystick.getMagnitude();

		if (Math.abs(magnitude) < DEAD_ZONE) {
			magnitude = 0;
		}

		if (magnitude > 1.0 ){
			magnitude = 1.0;
		}
		

		drive.polar (magnitude, angle);
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
		
	}

}
