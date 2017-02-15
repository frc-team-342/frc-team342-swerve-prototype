package org.usfirst.frc.team342.swerve_prototype;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

//Talon Controllers
public static final int FRDRIVE = 1;
public static final int BRDRIVE = 2;
public static final int BLDRIVE = 3;
public static final int FLDRIVE = 4;
public static final int FRTURN = 5;
public static final int BRTURN = 6;
public static final int BLTURN = 7;
public static final int FLTURN = 8;

//PWM Controllers
public static final int COLLECTOR_CONTROLLER = 0;
public static final int CONVEYER_CONTROLLER = 1;
public static final int CLIMB_CONTROLLER = 2;
public static final int GEAR_CONTROLLER = 3;
public static final int RING_LIGHT_1_CONTROL = 4;
public static final int RINGLIGHT_2_CONTROL = 5;
public static final int TARGETING_LIGHT_CONTROL = 6;
public static final int GREEN_LED_CONTROLLER = 7;
public static final int RED_LED_CONTROLLER = 8;
public static final int BLUE_LED_CONTROLLER = 9;

//Analog Sensors
public static final int ULTRASONIC_INTAKE_SENSOR = 0;
public static final int ULTRASONIC_GEAR_SENSOR = 1;
}