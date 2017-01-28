package org.usfirst.frc.team342.swerve_prototype.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystem extends Subsystem {
	
	private static final DriveSystem instance = new DriveSystem();

	private static boolean doneRotating;
	
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
	
	private static int frontRightOffset;
	private static int backRightOffset;
	private static int backLeftOffset;
	private static int frontLeftOffset;
	
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
		
		doneRotating = false;
		
		setUpRotationMotors();
	
		frRotationAmount = 0;
		brRotationAmount = 0;
		blRotationAmount = 0;
		flRotationAmount = 0;
		
		navx = new AHRS(SPI.Port.kMXP);
		navx.startLiveWindowMode();
	}
	
	public void setUpRotationMotors(){
		
		frontRightRotation.changeControlMode(TalonControlMode.Position);
		frontRightRotation.setP(1.0);
		frontRightRotation.setAllowableClosedLoopErr(15);
		frontRightRotation.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		
		backRightRotation.changeControlMode(TalonControlMode.Position);
		backRightRotation.setP(1.0);
		backRightRotation.setAllowableClosedLoopErr(15);
		backRightRotation.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		
		backLeftRotation.changeControlMode(TalonControlMode.Position);
		backLeftRotation.setP(1.0);
		backLeftRotation.setAllowableClosedLoopErr(15);
		backLeftRotation.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		
		frontLeftRotation.changeControlMode(TalonControlMode.Position);
		frontLeftRotation.setP(1.0);
		frontLeftRotation.setAllowableClosedLoopErr(1);
		frontLeftRotation.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		
	}
	
	public void driveWithJoystick(double x, double y, double z, double angle){
		SmartDashboard.putNumber("JoyStick X:", x);
		SmartDashboard.putNumber("Joystick Y:", y);
		SmartDashboard.putNumber("Joystick Angle:", angle);
		
		SmartDashboard.putNumber("FR Rotation Amount", frRotationAmount);
		SmartDashboard.putNumber("BR Rotation Amount", brRotationAmount);
		SmartDashboard.putNumber("BL Rotation Amount", blRotationAmount);
		SmartDashboard.putNumber("FL Rotation Amount", flRotationAmount);
		
		SmartDashboard.putNumber("FR enc val", frontRightRotation.getEncPosition());
		SmartDashboard.putNumber("BR enc val", backRightRotation.getEncPosition());
		SmartDashboard.putNumber("BL enc val", backLeftRotation.getEncPosition());
		SmartDashboard.putNumber("FL enc val", frontLeftRotation.getEncPosition());
		
		checkEncoders();
		
		
		frontRightRotation.set((angle / Math.PI));
		backRightRotation.set((angle / Math.PI));
		backLeftRotation.set((angle / Math.PI));
		frontLeftRotation.set((angle / Math.PI));
		
		
		//setAngle(1);
		
	}
	
	public void checkEncoders(){
		if(frontRightRotation.getEncPosition() > 4096 || frontRightRotation.getEncPosition() < -4096){
			resetEncoder(1);
			frRotationAmount += 1;
		}else if(backRightRotation.getEncPosition() > 4096 || backRightRotation.getEncPosition() < -4096){
			resetEncoder(2);
			brRotationAmount += 1;
		}else if(backLeftRotation.getEncPosition() > 4096 || backLeftRotation.getEncPosition() < -4096){
			resetEncoder(3);
			blRotationAmount += 1;
		}else if(frontLeftRotation.getEncPosition() > 4096 || frontLeftRotation.getEncPosition() < -4096){
			resetEncoder(4);
			flRotationAmount += 1;
		}
	}
	
	public void stopDriveMotors(){
		frontRightDrive.set(0);
		backRightDrive.set(0);
		backLeftDrive.set(0);
		frontLeftDrive.set(0);
	}
	
	public void setAngle(double goal){
		double angle = 1 / (goal * (512/45));
		
		if(frontRightRotation.getEncPosition() != angle){
			frontRightRotation.set(angle);
		}
		if(backRightRotation.getEncPosition() != angle){
			backRightRotation.set(angle);
		}
		if(backLeftRotation.getEncPosition() != angle){
			backLeftRotation.set(angle);
		}
		if(frontLeftRotation.getEncPosition() != angle){
			frontLeftRotation.set(angle);
		}
		
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
	
	public void resetEncoder(int motor){
		switch(motor){
		case 1: frontRightRotation.setEncPosition(0);
				break;
		case 2: backRightRotation.setEncPosition(0);
				break;
		case 3: backLeftRotation.setEncPosition(0);
				break;
		case 4: frontLeftRotation.setEncPosition(0);
				break;
		}
	}
	
	public int getEncoder(int motor){
		int out = 0;
		
		switch(motor){
			case 1: out = frontRightRotation.getEncPosition();
					break;
			case 2: out = backRightRotation.getEncPosition();
					break;
			case 3: out = backLeftRotation.getEncPosition();
					break;
			case 4: out = frontLeftRotation.getEncPosition();
					break;
		}
		
		return out;
	}
	
	
	public static DriveSystem getInstance(){
		return instance;
	}
	

}
