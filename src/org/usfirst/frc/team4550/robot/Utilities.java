package org.usfirst.frc.team4550.robot;

public class Utilities {

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
	
	
}
