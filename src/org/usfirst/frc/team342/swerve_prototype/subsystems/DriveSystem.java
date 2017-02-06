package org.usfirst.frc.team342.swerve_prototype.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystem extends Subsystem {
	
	private static final DriveSystem instance = new DriveSystem();
	
	private static CANTalon frontRightDrive;
	private static CANTalon backRightDrive;
	private static CANTalon backLeftDrive;
	private static CANTalon frontLeftDrive;
	
	private static CANTalon frontRightRotation;
	private static CANTalon backRightRotation;
	private static CANTalon backLeftRotation;
	private static CANTalon frontLeftRotation;
	
	private static int frRotationAmount;
	private static int brRotationAmount;
	private static int blRotationAmount;
	private static int flRotationAmount;
	
	private static AHRS navx;
	
	public DriveSystem(){
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void initializeDriveSystem(){
		frontRightDrive = new CANTalon(1);
		backRightDrive = new CANTalon(2);
		backLeftDrive = new CANTalon(3);
		frontLeftDrive = new CANTalon(4);
		
		frontRightRotation = new CANTalon(5);
		backRightRotation = new CANTalon(6);
		backLeftRotation = new CANTalon(7);
		frontLeftRotation = new CANTalon(8);
		
		setUpRotationMotors();
	
		frRotationAmount = 0;
		brRotationAmount = 0;
		blRotationAmount = 0;
		flRotationAmount = 0;
		
		navx = new AHRS(SPI.Port.kMXP);
		navx.startLiveWindowMode();
	}
	
	public void setUpRotationMotors(){
		frontRightRotation.reverseOutput(true);
		frontRightRotation.changeControlMode(TalonControlMode.Position);
		frontRightRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		backRightRotation.reverseOutput(true);
		backRightRotation.changeControlMode(TalonControlMode.Position);
		backRightRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		backLeftRotation.reverseOutput(true);
		backLeftRotation.changeControlMode(TalonControlMode.Position);
		backLeftRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		backLeftRotation.reverseSensor(true);
		
		frontLeftRotation.reverseOutput(true);
		frontLeftRotation.changeControlMode(TalonControlMode.Position);
		frontLeftRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
	}
	
	public void driveWithJoystick(double x, double y, double z, double angle){
		SmartDashboard.putNumber("JoyStick X:", x);
		SmartDashboard.putNumber("Joystick Y:", y);
		SmartDashboard.putNumber("Joystick Angle:", angle);
		
		SmartDashboard.putNumber("FR Rotation Amount", frRotationAmount);
		SmartDashboard.putNumber("BR Rotation Amount", brRotationAmount);
		SmartDashboard.putNumber("BL Rotation Amount", blRotationAmount);
		SmartDashboard.putNumber("FL Rotation Amount", flRotationAmount);
		
		SmartDashboard.putNumber("FR enc val", frontRightRotation.getPulseWidthPosition());
		SmartDashboard.putNumber("BR enc val", backRightRotation.getPulseWidthPosition());
		SmartDashboard.putNumber("BL enc val", backLeftRotation.getPulseWidthPosition());
		SmartDashboard.putNumber("FL enc val", frontLeftRotation.getPulseWidthPosition());
		
	}
	
	public void setAngle(double angle, CANTalon talon){
		double actual = talon.getPosition();
		
		if(actual > 0){
			angle = angle + Math.floor(actual);
			if(Math.abs(actual - angle) > 0.5){
				angle += 1;
			}
		}else{
			angle = angle - Math.floor(actual);
			if(Math.abs(angle - actual) > 0.5){
				angle -= 1;
			}
		}
		
		talon.set(angle);
		
	}

	public void stopDriveMotors(){
		frontRightDrive.set(0);
		backRightDrive.set(0);
		backLeftDrive.set(0);
		frontLeftDrive.set(0);
	}
	
	public void stopAngleMotors(){
		frontRightRotation.set(0);
		frontLeftRotation.set(0);
		backLeftRotation.set(0);
		backRightRotation.set(0);
	}
	
	public void stop(){
		frontRightRotation.set(0);
		frontLeftRotation.set(0);
		backLeftRotation.set(0);
		backRightRotation.set(0);
		
		frontRightDrive.set(0);
		frontLeftDrive.set(0);
		backLeftDrive.set(0);
		backRightDrive.set(0);
	}
	
	public static DriveSystem getInstance(){
		return instance;
	}
	
}
