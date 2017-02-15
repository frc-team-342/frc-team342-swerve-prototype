package org.usfirst.frc.team342.swerve_prototype.commands;

import org.usfirst.frc.team342.swerve_prototype.subsystems.Drivesystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class AdjustAngle extends Command {
private Joystick joystick;
	private int JOY_STICK=0;
	private int JOY_FRBUTTON=6;
	private int JOY_FLBUTTON=5;
	private int JOY_BRBUTTON=4;
	private int JOY_BLBUTTON=3;
	private int MotorNumber=0;
	private Drivesystem drive;
	
	public AdjustAngle (){
		joystick= new Joystick (JOY_STICK);
		drive = Drivesystem.getInstance();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	protected void execute() {
			drive.AngleAdjust(MotorNumber, 0.5);
	}

	protected void interrupted() {
		end();

	}

	protected void end() {

	}
	
	protected void initialize (){
		if (joystick.getRawButton(JOY_FRBUTTON)){
			MotorNumber=1;
		
		}
		if (joystick.getRawButton(JOY_FLBUTTON)){
			MotorNumber=4;
		}
		if (joystick.getRawButton(JOY_BRBUTTON)){
			MotorNumber=2;
		}
		if (joystick.getRawButton(JOY_BLBUTTON)){
			MotorNumber=3;
		}	
		}
		
	}
}
