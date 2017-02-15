package org.usfirst.frc.team342.swerve_prototype.commands;

import org.usfirst.frc.team342.swerve_prototype.OI;
import org.usfirst.frc.team342.swerve_prototype.subsystems.CamraSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class RotateCamera extends Command {
	
	CamraSystem CamControl;
	OI oi;
	Joystick Joy; 
	
	public RotateCamera(){
		CamControl = CamraSystem.getInstance();
		oi = OI.getInstance();
		Joy = oi.stick;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		CamControl.CameraAim(Joy.getAxis(AxisType.kX), Joy.getAxis(AxisType.kY));
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
