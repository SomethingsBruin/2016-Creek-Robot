package mechanism;

import org.usfirst.frc.team4550.robot.RobotMap;
import org.usfirst.frc.team4550.robot.Utilities;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import CCTalon.CCTalon;

public class Shooter 
{
	private static Shooter _instance;
	private CCTalon _shooterWheel;
	private CCTalon _backWheel;
	private DigitalInput _limitSwitch;
	
	private Shooter()
	{
		_shooterWheel = new CCTalon( RobotMap.SHOOTER_PORT, RobotMap.SHOOTER_REVERSE );
		_backWheel = new CCTalon( RobotMap.BACK_SHOOTER_PORT, RobotMap.BACK_SHOOTER_REVERSE );
		_limitSwitch = new DigitalInput(RobotMap.SHOOTER_LIMIT_PORT);
	}
	
	//Getter adjusted for singleton
	public static Shooter getInstance()
	{
		if( _instance == null )
		{
			_instance = new Shooter();
		}
		
		return _instance;
	}
	/**
	 * intakes the ball
	 * @param speed
	 */
	public void intake( double speed )
	{
		_shooterWheel.set( Utilities.normalize(-1.0* speed, -1 , 0 , 1) );
	}
	/**
	 * shoots the shooter
	 * @param speed
	 */
	public void outtake( double speed )
	{
		_shooterWheel.set( Utilities.normalize(speed, -1 , 0 , 1) );
		Timer.delay( 1.0 );
		_backWheel.set( 1.0);
		Timer.delay( .20 );
		while( _limitSwitch.get( ) )
		{
			;;//DO Nothing
		}
		Timer.delay( .15 );//.265 for real
		stop();
	}
	/**
	 * Stops the shooter
	 */
	public void stop()
	{
		_shooterWheel.set( 0 );
		_backWheel.set( 0 );	
	}
}