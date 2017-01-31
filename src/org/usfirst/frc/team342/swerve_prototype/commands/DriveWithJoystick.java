package org.usfirst.frc.team342.swerve_prototype.commands;

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
		joystick = new Joystick(JOY_STICK);
		

		requires(drive);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double angle = Math.atan(joystick.getRawAxis(1)/joystick.getRawAxis(0));
		double magnitude = (joystick.getRawAxis(1)*joystick.getRawAxis(1))+(joystick.getRawAxis(0)*joystick.getRawAxis(0));
		magnitude=Math.sqrt(magnitude);

		if (Math.abs(magnitude) < DEAD_ZONE) {
			magnitude = 0;
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
