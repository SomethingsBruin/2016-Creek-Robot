package org.usfirst.frc.team4550.robot;

public class Utilities {
	/**
	 * if value exceeds our upper or lower bound is set to the bound
	 * if our controller is at rest set 0 
	 * @param value
	 * @param min
	 * @param zero
	 * @param max
	 * @return
	 */
	public static double normalize(double value, double min, double zero, double max)
	{
		if(value == zero)
		{
			value = 0;
		}
		if(value < min) 
		{
			value = min;
		}
		if(value > max) 
		{
			value = max;
		}
		return value; 
	}	
	/**
	 * exponential function for driver
	 */
	public static double exp( double value, double exp )
	{
		return Math.pow(value, exp);
	}
	
	public static double fixInput( double value )
	{
		if( Math.abs(value) < .15 )
		{
			return 0;
		}
		if( value > .15 )
		{
			value -= .15;
		}
		else if( value < -.15 )
		{
			value += .15;
		}
		return value;
	}
	
	
}
