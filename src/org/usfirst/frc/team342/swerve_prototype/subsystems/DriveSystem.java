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
	
	private static double test;
	
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
		
		SmartDashboard.putBoolean("initialized:", true);
	}
	
	public void setUpRotationMotors(){
		
		SmartDashboard.putBoolean("rotation motors setup:", true);
		
		frontRightRotation.disable();
		frontRightRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		frontRightRotation.changeControlMode(TalonControlMode.Position);
		frontRightRotation.setP(0.3);
		frontRightRotation.reverseSensor(true);
		frontRightRotation.reverseOutput(true);
		frontRightRotation.setEncPosition(frontRightRotation.getPulseWidthPosition() % 4096);
		frontRightRotation.enable();
		
		
		//put these in the right order... we don't know exactly what, but it's not right
		backRightRotation.disable();
		backRightRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		backRightRotation.changeControlMode(TalonControlMode.Position);
		backRightRotation.reverseSensor(true);
		backRightRotation.setP(0.3);
		backRightRotation.setEncPosition(frontRightRotation.getPulseWidthPosition() % 4096);
		backRightRotation.enable();
		
		backLeftRotation.disable();
		backLeftRotation.reverseSensor(true);
		backLeftRotation.setP(0.3);
		backLeftRotation.changeControlMode(TalonControlMode.Position);
		backLeftRotation.setEncPosition(frontRightRotation.getPulseWidthPosition() % 4096);
		backLeftRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		backLeftRotation.enable();
		backLeftRotation.reverseOutput(true);
		
		frontLeftRotation.disable();
		frontLeftRotation.reverseSensor(true);
		frontLeftRotation.setP(0.3);
		frontLeftRotation.changeControlMode(TalonControlMode.Position);
		frontLeftRotation.setEncPosition(frontRightRotation.getPulseWidthPosition() % 4096);
		frontLeftRotation.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		frontLeftRotation.enable();
		
	}
	
	public void driveWithJoystick(double x, double y, double z, double angle){
		
		angle = (Math.abs(angle + 360) % 360) / 360;
		
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
		
		setAngle(angle, frontRightRotation);
		
	}
	
	public void setAngle(double angle, CANTalon talon){
		SmartDashboard.putNumber("Angle Before:", angle);
		double actual = talon.getPosition();
		
		if(actual > 0){
			angle = angle + Math.floor(actual);
		}else{
			angle = angle - 1;
			angle = angle + Math.ceil(actual);
		}
		
		SmartDashboard.putNumber("Angle Mid", angle);
			
		if(Math.abs(actual - angle) > 0.5){
			if(angle > actual){
				angle -= 1;
			}else{
				angle += 1;
			}
		}
		
		SmartDashboard.putNumber("angle:", angle);
		SmartDashboard.putNumber("Actual:", actual);
		
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
