package Chassis;

import CCTalon.CCTalon;

import org.usfirst.frc.team4550.robot.RobotMap;
import org.usfirst.frc.team4550.robot.Utilities;

public class Chassis
{
	private CCTalon _leftTalon;
	private CCTalon _rightTalon;
	
	private static Chassis _instance;
	
	private Chassis() 
	{
		_leftTalon = new CCTalon(RobotMap.LEFT_TALON_PORT, RobotMap.LEFT_TALON_REVERSE);
		_rightTalon = new CCTalon(RobotMap.RIGHT_TALON_PORT, RobotMap.RIGHT_TALON_REVERSE);
		
	}
	
	public static Chassis getChassis()
	{
		if (_instance == null)
		{
			_instance = new Chassis();
		}
		
		return _instance;
	}
	
	public void drive(double ySpeed, double xSpeed)
	{
		_leftTalon.set( Utilities.normalize( ySpeed + xSpeed, -1.0, 0, 1.0 ) );
		_rightTalon.set( Utilities.normalize( ySpeed - xSpeed, -1.0, 0, 1.0 ) );
	}
	
	public void turn(double speed)
	{
		_leftTalon.set(speed);
		_rightTalon.set(-speed);
	}
	
	public void stop()
	{
		_leftTalon.set(0);
		_rightTalon.set(0);
	}
}
