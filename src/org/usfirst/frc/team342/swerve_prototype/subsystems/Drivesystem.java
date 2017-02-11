package org.usfirst.frc.team342.swerve_prototype.subsystems;

import org.usfirst.frc.team342.swerve_prototype.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivesystem extends Subsystem {

	private static final Drivesystem instance = new Drivesystem();

	private CANTalon FRDrive;
	private CANTalon BRDrive;
	private CANTalon BLDrive;
	private CANTalon FLDrive;
		
	private CANTalon FRTurn;
	private CANTalon BRTurn;
	private CANTalon BLTurn;
	private CANTalon FLTurn;
		
	private AHRS NavX;

	private Drivesystem() {
		initializeDriveSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated guber method stub

	}

	public static Drivesystem getInstance() {
		return instance;
	}
	
	public void initializeDriveSystem(){
		
		FRDrive = new CANTalon(RobotMap.frdrive);
		FRTurn = new CANTalon(RobotMap.frturn);
		BRDrive = new CANTalon(RobotMap.brdrive);
		BRTurn = new CANTalon(RobotMap.brturn);
		BLDrive = new CANTalon(RobotMap.bldrive);
		BLTurn = new CANTalon(RobotMap.blturn);
		FLDrive = new CANTalon(RobotMap.frdrive);
		FLTurn = new CANTalon(RobotMap.flturn);
		
		setUpRotationMotors();
		
		NavX = new AHRS(SPI.Port.kMXP);
		NavX.startLiveWindowMode();
	
	}
	
	public void setUpRotationMotors(){
		
		FRTurn.disable();
		FRTurn.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		FRTurn.changeControlMode(TalonControlMode.Position);
		FRTurn.setP(0.3);
		FRTurn.reverseSensor(true);
		FRTurn.reverseOutput(true);
		FRTurn.setEncPosition(FRTurn.getPulseWidthPosition() % 4096);
		FRTurn.enable();
		
		BRTurn.disable();
		BRTurn.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		BRTurn.changeControlMode(TalonControlMode.Position);
		BRTurn.setP(0.3);
		BRTurn.reverseSensor(true);
		BRTurn.reverseOutput(true);
		BRTurn.setEncPosition(BRTurn.getPulseWidthPosition() % 4096);
		BRTurn.enable();
		
		BLTurn.disable();
		BLTurn.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		BLTurn.changeControlMode(TalonControlMode.Position);
		BLTurn.setP(0.3);
		BLTurn.reverseOutput(true);
		BLTurn.reverseSensor(true);
		BLTurn.setEncPosition(BLTurn.getPulseWidthPosition() % 4096);
		BLTurn.enable();
		
		FLTurn.disable();
		FLTurn.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		FLTurn.changeControlMode(TalonControlMode.Position);
		FLTurn.setP(0.3);
		FLTurn.reverseSensor(true);
		FLTurn.reverseOutput(true);
		FLTurn.setEncPosition(FLTurn.getPulseWidthPosition() % 4096);
		FLTurn.enable();
		
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
			// Talon.setControlMode(CANTalon.TalonControlMode.Voltage.value);
			Talon.set(speed);
		}
	}

	public void DriveWheelsVolts(double speed) {
		if (FRDrive.getControlMode() == CANTalon.TalonControlMode.Voltage
				&& FRDrive.getControlMode() == BRDrive.getControlMode()
				&& FRDrive.getControlMode() == FLDrive.getControlMode()
				&& FRDrive.getControlMode() == BLDrive.getControlMode()) {

			FRDrive.set(speed);
			BRDrive.set(speed);
			FLDrive.set(speed);
			FRDrive.set(speed);

		}
	}

	public void StopDrive() {

		if (FRTurn.getControlMode() == CANTalon.TalonControlMode.Position
				&& FRTurn.getControlMode() == BRTurn.getControlMode()
				&& FRTurn.getControlMode() == FLTurn.getControlMode()
				&& FRTurn.getControlMode() == BLTurn.getControlMode()) {
			// position mode
				FRTurn.set(FRTurn.getPosition());
				FLTurn.set(FLTurn.getPosition());
				BRTurn.set(FRTurn.getPosition());
				BLTurn.set(BLTurn.getPosition());

		} else {
			// not position mode
				FRTurn.set(0.0);
				BRTurn.set(0.0);
				FLTurn.set(0.0);
				FRTurn.set(0.0);

		}

	}

	public void StopTurn() {
		if (FRDrive.getControlMode() == CANTalon.TalonControlMode.Position
				&& FRDrive.getControlMode() == BRDrive.getControlMode()
				&& FRDrive.getControlMode() == FLDrive.getControlMode()
				&& FRDrive.getControlMode() == BLDrive.getControlMode()) {
			// position mode
				FRDrive.set(FRDrive.getPosition());
				FLDrive.set(FLDrive.getPosition());
				BRDrive.set(FRDrive.getPosition());
				BLDrive.set(BLDrive.getPosition());

		} else {
			// not position mode
				FRTurn.set(0.0);
				BRDrive.set(0.0);
				FLDrive.set(0.0);
				FRDrive.set(0.0);

		}
	}

	public void DWJmanup(double angle, double speed, double rotation, boolean FelO) {
		// FeLO = field orientation
		
		// angles
		double FRWheel;
		double FLWheel;
		double BRWheel;
		double BLWheel;

		// Speeds
		double FRSpeed;
		double FLSpeed;
		double BRSpeed;
		double BLSpeed;
		
		//rotation
		double xangle;
		double yangle;
		double xFRrot;
		double yFRrot;

		// From the NavX
		double GyRo = NavX.getAngle();
		xangle = Math.cos(angle * 2 * Math.PI);
		yangle = Math.sin(angle * 2 * Math.PI);
		xangle = xangle * speed;
		yangle = yangle * speed;
		xFRrot = Math.cos((3*Math.PI)/4);
		yFRrot = Math.sin((3*Math.PI)/4);
		xFRrot = xFRrot * rotation;
		yFRrot = yFRrot * rotation;
		
		// setting individual wheels angle
		FRWheel = Math.atan ((yangle + yFRrot)/(xangle + xFRrot));
		FLWheel = angle;
		BRWheel = angle;
		BLWheel = angle;

		// setting individual speed angle
		FRSpeed = Math.sqrt(Math.pow(xangle + xFRrot, 2)+ (Math.pow(yangle + yFRrot, 2)));
		FLSpeed = speed;
		BRSpeed = speed;
		BLSpeed = speed;

		// setting angle
		setAngle(FRWheel, FRTurn);
		setAngle(FLWheel, FLTurn);
		setAngle(BRWheel, BRTurn);
		setAngle(BLWheel, BLTurn);

		// setting talons to speed
		FRDrive.set(FRSpeed);
		FLDrive.set(FLSpeed);
		BRDrive.set(BRSpeed);
		BLDrive.set(BLSpeed);
		
		outputInfo();
		
	}

	public void setAngle(double angle, CANTalon talon){
		
		double actual = talon.getPosition();
		
		if(actual > 0){
			angle = angle + Math.floor(actual);
		}else{
			angle = angle - 1;
			angle = angle + Math.ceil(actual);
		}
			
		if(Math.abs(actual - angle) > 0.5){
			if(angle > actual){
				angle -= 1;
			}else{
				angle += 1;
			}
		}

		talon.set(angle);
		
	}
	
	public void Spinning(double rotation) {
		setAngle(0.375, FRTurn);
		setAngle(0.125, FLTurn);
		setAngle(0.625, BRTurn);
		setAngle(0.875, BLTurn);

		// for rotation
		FRDrive.set(rotation);
		BRDrive.set(rotation);
		BLDrive.set(rotation);
		FLDrive.set(rotation);
	}

	public void ResetGyro() {
		NavX.reset();
	}
	
	public void ResetIncoder(CANTalon talon, int offset){
		//talon.setEncPosition((talon.getPulseWidthPosition() % 4096) + offset);
		int holdnum = talon.getPulseWidthPosition();
			if (holdnum > 0){
			talon.setEncPosition((holdnum % 4096)+ offset);
		}
			else{
			holdnum = holdnum * -1;	
			holdnum = holdnum % 4096;
			holdnum = 4096 - holdnum;
			talon.setEncPosition((holdnum)+ offset);
			}
	
	}
	
	public void outputInfo(){
		SmartDashboard.putNumber("Gyro:", NavX.getAngle());
		
		SmartDashboard.putNumber("FRencPos:", FRTurn.getPulseWidthPosition());
		SmartDashboard.putNumber("BRencPos:", BRTurn.getPulseWidthPosition());
		SmartDashboard.putNumber("BLencPos:", BLTurn.getPulseWidthPosition());
		SmartDashboard.putNumber("FLencPos:", FLTurn.getPulseWidthPosition());
	}
}
