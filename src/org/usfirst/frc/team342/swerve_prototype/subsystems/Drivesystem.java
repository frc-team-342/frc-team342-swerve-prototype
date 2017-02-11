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
		FLDrive = new CANTalon(RobotMap.fldrive);
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
		
		// Converting the angle from a number 0.0 to 1.0 to degrees, then to radians.
		double angleRad = (angle * 360 * Math.PI) / 180;
		
		SmartDashboard.putNumber("AngelRaw: ", angle);
		SmartDashboard.putNumber("AngleRad: ", angleRad);
		
		// angles
		double FRRotation = angle;
		double FLRotation = angle;
		double BRRotation = angle;
		double BLRotation = angle;
		
		double tempX = 0;
		double tempY = 0;

		// Speeds
		double FRSpeed = speed;
		double FLSpeed = speed;
		double BRSpeed = speed;
		double BLSpeed = speed;
		
		// From the NavX
		double GyRo = NavX.getAngle();

		// setting individual wheels angle
		if((rotation < -0.15 || rotation > 0.15) && speed > 0.15){
			if(angleRad > 0 && angleRad < Math.PI/2){
			
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(45) * rotation);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(45) * rotation * -1);
				FRRotation = (Math.atan(tempX / tempY));
				FRSpeed = (tempX / Math.sin(FRRotation));
		
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(135) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(135) * rotation * -1);
				BRRotation = (Math.atan(tempX / tempY));
				BRSpeed = (tempX / Math.sin(BRRotation));
			
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(225) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(225) * rotation);
				BLRotation = (Math.atan(tempX / tempY));
				BLSpeed = (tempX / Math.sin(BLRotation));
			
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(315) * rotation);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(315) * rotation);
				FLRotation = (Math.atan(tempX / tempY));
				FLSpeed = (tempX / Math.sin(FRRotation));
			
				FRRotation = (((180 / Math.PI) * FRRotation) / 360);
				BRRotation = (((180 / Math.PI) * BRRotation) / 360);
				BLRotation = (((180 / Math.PI) * BLRotation) / 360);
				FLRotation = (((180 / Math.PI) * FLRotation) / 360);
				
				// setting angle
				setAngle(FRRotation, FRTurn);
				setAngle(BRRotation, BRTurn);
				setAngle(BLRotation, BLTurn);
				setAngle(FLRotation, FLTurn);

				// setting talons to speed
				//FRDrive.set(FRSpeed);
				//FLDrive.set(FLSpeed);
				//BRDrive.set(BRSpeed);
				//BLDrive.set(BLSpeed);
			
			}else if(angleRad >= Math.PI/2 && angleRad < Math.PI){
			
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(45) * rotation);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(45) * rotation * -1);
				FRRotation = (Math.atan(tempX / tempY));
				FRSpeed = (tempX / Math.sin(FRRotation));
		
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(135) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(135) * rotation * -1);
				BRRotation = (Math.atan(tempX / tempY));
				BRSpeed = (tempX / Math.sin(BRRotation));
			
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(225) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(225) * rotation);
				BLRotation = (Math.atan(tempX / tempY));
				BLSpeed = (tempX / Math.sin(BLRotation));
			
				tempX = (Math.cos(angleRad) * speed) + (Math.cos(315) * rotation);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(315) * rotation);
				FLRotation = (Math.atan(tempX / tempY));
				FLSpeed = (tempX / Math.sin(FRRotation));
			
				FRRotation = (((180 / Math.PI) * FRRotation) / 360);
				BRRotation = (((180 / Math.PI) * BRRotation) / 360);
				BLRotation = (((180 / Math.PI) * BLRotation) / 360);
				FLRotation = (((180 / Math.PI) * FLRotation) / 360);
				
				// setting angle
				setAngle(FRRotation, FRTurn);
				setAngle(BRRotation, BRTurn);
				setAngle(BLRotation, BLTurn);
				setAngle(FLRotation, FLTurn);

				// setting talons to speed
				//FRDrive.set(FRSpeed);
				//FLDrive.set(FLSpeed);
				//BRDrive.set(BRSpeed);
				//BLDrive.set(BLSpeed);
			
			}else if(angle >= Math.PI && angle < ((3 * Math.PI) / 2)){
			
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(45) * rotation);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(45) * rotation * -1);
				FRRotation = (Math.atan(tempX / tempY));
				FRSpeed = (tempX / Math.sin(FRRotation));
		
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(135) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(135) * rotation * -1);
				BRRotation = (Math.atan(tempX / tempY));
				BRSpeed = (tempX / Math.sin(BRRotation));
			
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(225) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(225) * rotation);
				BLRotation = (Math.atan(tempX / tempY));
				BLSpeed = (tempX / Math.sin(BLRotation));
			
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(315) * rotation);
				tempY = (Math.sin(angleRad) * speed * -1) + (Math.sin(315) * rotation);
				FLRotation = (Math.atan(tempX / tempY));
				FLSpeed = (tempX / Math.sin(FRRotation));
			
				FRRotation = (((180 / Math.PI) * FRRotation) / 360);
				BRRotation = (((180 / Math.PI) * BRRotation) / 360);
				BLRotation = (((180 / Math.PI) * BLRotation) / 360);
				FLRotation = (((180 / Math.PI) * FLRotation) / 360);
				
				// setting angle
				setAngle(FRRotation, FRTurn);
				setAngle(BRRotation, BRTurn);
				setAngle(BLRotation, BLTurn);
				setAngle(FLRotation, FLTurn);

				// setting talons to speed
				//FRDrive.set(FRSpeed);
				//FLDrive.set(FLSpeed);
				//BRDrive.set(BRSpeed);
				//BLDrive.set(BLSpeed);
			
			}else if(angle >= ((3 * Math.PI) / 2) && angle < (2 * Math.PI)){
			
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(45) * rotation);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(45) * rotation * -1);
				FRRotation = (Math.atan(tempX / tempY));
				FRSpeed = (tempX / Math.sin(FRRotation));
		
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(135) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(135) * rotation * -1);
				BRRotation = (Math.atan(tempX / tempY));
				BRSpeed = (tempX / Math.sin(BRRotation));
			
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(225) * rotation * -1);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(225) * rotation);
				BLRotation = (Math.atan(tempX / tempY));
				BLSpeed = (tempX / Math.sin(BLRotation));
			
				tempX = (Math.cos(angleRad) * speed * -1) + (Math.cos(315) * rotation);
				tempY = (Math.sin(angleRad) * speed) + (Math.sin(315) * rotation);
				FLRotation = (Math.atan(tempX / tempY));
				FLSpeed = (tempX / Math.sin(FRRotation));
			
				FRRotation = (((180 / Math.PI) * FRRotation) / 360);
				BRRotation = (((180 / Math.PI) * BRRotation) / 360);
				BLRotation = (((180 / Math.PI) * BLRotation) / 360);
				FLRotation = (((180 / Math.PI) * FLRotation) / 360);
			
				// setting angle
				setAngle(FRRotation, FRTurn);
				setAngle(BRRotation, BRTurn);
				setAngle(BLRotation, BLTurn);
				setAngle(FLRotation, FLTurn);

				// setting talons to speed
				//FRDrive.set(FRSpeed);
				//FLDrive.set(FLSpeed);
				//BRDrive.set(BRSpeed);
				//BLDrive.set(BLSpeed);
				
			}else{
				SmartDashboard.putString("Info: ", "This is not supposed to show up, if it does, then the code is broken.");
				
			}
			
		}else if(speed < 0.15 && (rotation < -0.15 || rotation > 0.15)){
			Spinning(rotation);
			
		}else if(speed > 0.15 && (rotation > -0.15 || rotation < 0.15)){
			
			// setting angle
			setAngle(FRRotation, FRTurn);
			setAngle(BRRotation, BRTurn);
			setAngle(BLRotation, BLTurn);
			setAngle(FLRotation, FLTurn);

			// setting talons to speed
			//FRDrive.set(FRSpeed);
			//FLDrive.set(FLSpeed);
			//BRDrive.set(BRSpeed);
			//BLDrive.set(BLSpeed);
		}
		
		//Commented out to be used in the Statments above due to having a temp value.
		// setting individual speed angle
		//FRSpeed = speed;
		//FLSpeed = speed;
		//BRSpeed = speed;
		//BLSpeed = speed;
		
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
	
	public void Spinning(double speed) {
		setAngle(0.375, FRTurn);
		setAngle(0.125, FLTurn);
		setAngle(0.625, BRTurn);
		setAngle(0.875, BLTurn);

		// for rotation
		//FRDrive.set(speed);
		//BRDrive.set(speed);
		//BLDrive.set(speed);
		//FLDrive.set(speed);
	}

	public void ResetGyro() {
		NavX.reset();
	}
	
	public void ResetEncoder(CANTalon talon, int offset){
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
		
		SmartDashboard.putNumber("FRencPos:", FRTurn.getPosition());
		SmartDashboard.putNumber("BRencPos:", BRTurn.getPosition());
		SmartDashboard.putNumber("BLencPos:", BLTurn.getPosition());
		SmartDashboard.putNumber("FLencPos:", FLTurn.getPosition());
	}
}
