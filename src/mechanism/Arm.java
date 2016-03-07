package mechanism;

import org.usfirst.frc.team4550.robot.RobotMap;
import org.usfirst.frc.team4550.robot.Utilities;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import CCTalon.CCTalon;


public class Arm 
{
	private CCTalon _arm;
	private static Arm _instance;
	private AnalogPotentiometer _potentiometer;
	
	private DigitalInput _lowerSwitch;
	private DigitalInput _upperSwitch;
	
	/**
	 * Creates our arm talon
	 */
	private Arm() 
	{
		_arm = new CCTalon ( RobotMap.ARM_PORT, false );
		_potentiometer = new AnalogPotentiometer(RobotMap.POTENTIOMETER_PORT);
		_lowerSwitch = new DigitalInput( RobotMap.LOWER_ARM_LIMIT_PORT );
		_upperSwitch = new DigitalInput( RobotMap.UPPER_ARM_LIMIT_PORT );
	}
	
	/**
	 * Gets the instance of our arm
	 * @return Our arm
	 */
	public static Arm getArm()
	{
		if( _instance == null )
		{
			_instance = new Arm();
		}
		
		return _instance;
		
	}
	
	/**
	 * Moves the arm up and down
	 * @param speed The speed at which we move our arm
	 */
	public void move( double speed )
	{
		//System.out.println( _potentiometer.get() );
		if( ( speed < 0 && !_upperSwitch.get() ) || ( speed > 0 && !_lowerSwitch.get() ) )
		{
			this.stop();
			return;
		}
		if( speed > .75 )
		{
			speed = .75;
		}
		else if( speed < -.90 )
		{
			speed = -.90;
		}
		_arm.set(speed);
	}
	
	/**
	 * Stops moving our arm. Slight charge upward to counter gravity.
	 */
	public void stop()
	{
		_arm.set(0.00);
	}
	
	/**
	 * Sets the speed for arm movement
	 * @param speed
	 */
	public void set(double speed, double angle)
	{
		if(angle > _potentiometer.get())
		{
			move(speed);
			
		} 
		else if(angle < _potentiometer.get())
		{
			move(-speed);
		}
		else
		{
			stop();
		}
	}
	
	public boolean getUpperSwitch( )
	{
		return _upperSwitch.get();
	}
	
	public boolean getLowerSwitch( )
	{
		return _lowerSwitch.get();
	}
	
	public double getPotentiometre( )
	{
		return _potentiometer.get();
	}
}
