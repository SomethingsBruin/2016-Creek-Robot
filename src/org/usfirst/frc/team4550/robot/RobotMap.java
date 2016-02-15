package org.usfirst.frc.team4550.robot;

public  class RobotMap {

	public static final int A_BUTTON = 1;
	public static final int B_BUTTON = 2;
	public static final int X_BUTTON = 3;
	public static final int Y_BUTTON = 4;
	public static final int LB_BUTTON = 5;
	public static final int RB_BUTTON = 6;
	public static final int BACK_BUTTON = 7;
	public static final int START_BUTTON = 8;
	//These buttons are when you push down the left and right circle pad
	public static final int L3_BUTTON = 9;
	public static final int R3_BUTTON = 10;
	
	public static final int LEFT_X = 0;
	public static final int LEFT_Y = 1;
	public static final int LEFT_2 = 2;
	public static final int RIGHT_2 = 3;
	public static final int RIGHT_X = 4;
	public static final int RIGHT_Y = 0;

	public static final double LEFT_Y_ZERO = -0.0078125;
	public static final double RIGHT_Y_ZERO = -0.0078125;

	public static final int LEFT_TALON_PORT = 4;
	public static final int RIGHT_TALON_PORT = 5;
	public static final boolean LEFT_TALON_REVERSE = true;
	public static final boolean RIGHT_TALON_REVERSE = false;
	
	public static final int ARM_PORT = 6;
	public static final int SHOOTER_PORT = 7;
	public static final int BACK_SHOOTER_PORT = 8;
	
	public static final boolean SHOOTER_REVERSE = false;
	public static final boolean BACK_SHOOTER_REVERSE = true;
	
	public static final int SHOOTER_LIMIT_PORT = -1;
	
	public static final int GYRO_PORT = 1;
	
	public static final int ENCODER_PORT_A_LEFT = 0;
	public static final int ENCODER_PORT_B_LEFT = 1;
	
	public static final int ENCODER_PORT_A_RIGHT = 2;
	public static final int ENCODER_PORT_B_RIGHT = 3;
	
	public static final int TICKS_PER_INCH_LEFT = -1;
	public static final double INCHES_PER_TICK_RIGHT = -.02769;
	
	public static final int POTENTIOMETER_PORT = 0;
}
