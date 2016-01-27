package mechanism;

import org.usfirst.frc.team4550.robot.RobotMap;
import org.usfirst.frc.team4550.robot.Utilities;

import CCTalon.CCTalon;

public class Shooter 
{
	private static Shooter _instance;
	private CCTalon _leftWheel;
	private CCTalon _rightWheel;
	private CCTalon _backWheel;
	
	private Shooter()
	{
		_leftWheel = new CCTalon( RobotMap.LEFT_SHOOTER_PORT, RobotMap.LEFT_SHOOTER_REVERSE );
		_rightWheel = new CCTalon( RobotMap.RIGHT_SHOOTER_PORT, RobotMap.RIGHT_SHOOTER_REVERSE);
		_backWheel = new CCTalon( RobotMap.BACK_SHOOTER_PORT, RobotMap.BACK_SHOOTER_REVERSE );
	}
	
	public static Shooter getInstance()
	{
		if( _instance == null )
		{
			_instance = new Shooter();
		}
		
		return _instance;
	}
	
	public void intake( double speed )
	{
		_rightWheel.set( Utilities.normalize(speed, -1 , 0 , 1) );
		_leftWheel.set( Utilities.normalize(speed, -1 , 0 , 1) );
	}
	
	public void outtake( double speed )
	{
		_rightWheel.set( Utilities.normalize(speed, -1 , 0 , 1) );
		_leftWheel.set( Utilities.normalize(speed, -1 , 0 , 1) );
		_backWheel.set( Utilities.normalize(speed, -1 , 0 , 1) );
	}
	
	public void stop()
	{
		_rightWheel.set( 0 );
		_leftWheel.set( 0 );
		_backWheel.set( 0 );	
	}
}