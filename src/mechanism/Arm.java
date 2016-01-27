package mechanism;

import org.usfirst.frc.team4550.robot.RobotMap;
import org.usfirst.frc.team4550.robot.Utilities;

import CCTalon.CCTalon;


public class Arm 
{
	private CCTalon _arm;
	private static Arm _instance;
	
	
	private Arm() 
	{
		_arm = new CCTalon ( RobotMap.ARM_PORT, false );
	}
	
	public static Arm getArm()
	{
		if( _instance == null )
		{
			_instance = new Arm();
		}
		
		return _instance;
		
	}
	
	public void move( double speed )
	{
		_arm.set( Utilities.normalize(speed, -1, 0, 1) );
	}
	
	public void stop()
	{
		_arm.set(0);
	}
}
