package org.usfirst.frc.team342.swerve_prototype.commands;

import org.usfirst.frc.team342.swerve_prototype.OI;
import org.usfirst.frc.team342.swerve_prototype.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	
	private DriveSystem drive;
	private OI oi;
	private Joystick joy;
	
	@Override
	protected void initialize() {
		drive = drive.getInstance();
		oi = oi.getInstance();
		joy = oi.stick;
	}

	@Override
	protected void execute() {
		drive.driveWithJoystick(joy.getAxis(AxisType.kX), joy.getAxis(AxisType.kY), joy.getAxis(AxisType.kZ), joy.getDirectionDegrees());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
