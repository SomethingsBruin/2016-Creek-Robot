package Chassis;

import CCTalon.CCTalon;
import org.usfirst.frc.team4550.robot.RobotMap;
import org.usfirst.frc.team4550.robot.Utilities;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public class Chassis
{
	private CCTalon _leftTalon;
	private CCTalon _rightTalon;
	private AnalogGyro _gyro;
	private Encoder _encoderLeft;
	private Encoder _encoderRight;
	
	private static Chassis _instance;
	
	/**
	 *Creates a left and right Talon for wheels
	 */
	private Chassis() 
	{
		_leftTalon = new CCTalon(RobotMap.LEFT_TALON_PORT, RobotMap.LEFT_TALON_REVERSE);
		_rightTalon = new CCTalon(RobotMap.RIGHT_TALON_PORT, RobotMap.RIGHT_TALON_REVERSE);
		_gyro = new AnalogGyro(RobotMap.GYRO_PORT);
		//_encoderLeft = new Encoder(RobotMap.ENCODER_PORT_A_LEFT, RobotMap.ENCODER_PORT_B_LEFT);
		_encoderRight = new Encoder(RobotMap.ENCODER_PORT_A_RIGHT, RobotMap.ENCODER_PORT_B_RIGHT);
		//_encoderLeft.setDistancePerPulse( RobotMap.TICKS_PER_INCH_LEFT );
		_encoderRight.setDistancePerPulse( RobotMap.INCHES_PER_TICK_RIGHT );	
	}
	
	/**
	 * Singleton method
	 * @return Our chassis
	 */
	public static Chassis getChassis()
	{
		if (_instance == null)
		{
			_instance = new Chassis();
		}
		
		return _instance;
	}
	
	/**
	 * Drives our rhino-treads at certain speed
	 * @param ySpeed 
	 * @param xSpeed
	 */
	public void drive(double ySpeed, double xSpeed)
	{
		_leftTalon.set( Utilities.normalize( ySpeed - xSpeed, -.9, 0, .9 ) );
		_rightTalon.set( Utilities.normalize( ySpeed + xSpeed, -.9, 0, .9 ) );
	}
	
	/**
	 * Turns the rhino-treads
	 * @param speed
	 */
	public void turn(double speed)
	{
		//System.out.println( speed );
		_leftTalon.set(speed);
		_rightTalon.set(-speed);
	}
	
	/**
	 * Stops the rhino-treads
	 */
	public void stop()
	{
		_leftTalon.set(0);
		_rightTalon.set(0);
	}
	
	public double getAngle()
	{
		return _gyro.getAngle();
	}
	
	public double getLeftEncoder()
	{
		return _encoderLeft.get();
	}
	
	public double getRightEncoder()
	{
		return _encoderRight.get();
	}
	
	public void move(double distance, double speed)
	{
		_encoderRight.reset();
		while( _encoderRight.getDistance() < distance )
		{

			drive( speed, _gyro.getAngle()/-100 );
		}
		stop();
	}
	
	public void reset()
	{
		_gyro.reset();
		//_encoderLeft.reset();
		_encoderRight.reset();
	}
	
	public void turnToAngle( double angle, double speed )
	{
		 boolean done = false;
	        
	        //Default speed is at 0.7
	        long maxTime = 4000;//4 seconds
	        double time = 0.0;
	        
	        //PID constants
	        double Kp = 2.50;
	        double Ki = 0.035;
	        double Kd = 0.015;
	        
	        //PID variables
	        double moveSpeed = speed;
	        double error = 0.0;
	        double prevError = 0.0;
	        double errorSum = 0.0;
	        
	        angle += _gyro.getAngle();
	        
	        
	        time = System.currentTimeMillis();

	        //PID loop
	        while ( !done )
	        {                    
	            prevError = error;
	            System.out.println( "Angle: " + angle + " GYRO: " + _gyro.getAngle() );
	            error = ( angle - _gyro.getAngle() ) / 100.0;
	            errorSum += error;
	            errorSum = Utilities.normalize( errorSum, -5, 0, 5 );
	            
	            //System.out.println( "error: " + error + " errorSum: " + errorSum );
	            
	            double p = error * Kp;
	            double i = errorSum * Ki;
	            double d = (error - prevError) * Kd;       

	            this.turn( Utilities.normalize(-1*(p + i + d), -moveSpeed, 0, moveSpeed ) ); 

	            if ( (Math.abs( errorSum ) < 0.01) || (System.currentTimeMillis() > time+maxTime) )
	            {
	                done = true;
	            }
	        }
	        
	        this.stop(); 
	        done = false;
	}
}
