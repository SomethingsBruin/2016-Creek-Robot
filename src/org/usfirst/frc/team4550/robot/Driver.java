package org.usfirst.frc.team4550.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Driver {

	private Joystick _joystick; 
	private static Driver _instance; 
	
	
	private Driver()
	{
		_joystick = new Joystick(0);
	}
	
	public static Driver getInstance(){
		if(_instance == null)
			_instance = new Driver();
		return _instance;
	}
	
	public void displayStats()
	{
		for(int i = 0; i < _joystick.getAxisCount(); i++)
		{
			System.out.print(i+ ". "+_joystick.getRawAxis(i) + "  ");
		}
		System.out.print( "6. " + _joystick.getRawAxis(6));
		System.out.println();
		for(int i = 1; i <= _joystick.getButtonCount(); i++)
		{
			System.out.print(i + ". " + _joystick.getRawButton(i) + "  ");
		}
		System.out.println();
		Timer.delay(1);
	}
	
	public double getAxis(int axisNumber)
	{
		return _joystick.getRawAxis(axisNumber);
	}
	
	public boolean getButton(int buttonNumber)
	{
		return _joystick.getRawButton(buttonNumber);
	}
	
	
}
