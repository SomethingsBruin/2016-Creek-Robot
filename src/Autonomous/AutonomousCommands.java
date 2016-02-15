package Autonomous;

import mechanism.Arm;
import mechanism.Shooter;
import Chassis.Chassis;

public class AutonomousCommands
{
	private Chassis _chassis;
	
	public AutonomousCommands()
	{
		_chassis = Chassis.getChassis();
	}
	
	public void runAutonomous(int location, int defense, double speed)
	{
		switch ( defense )
		{
		case 0:
			crossLowBar(speed);
			break;
		case 1:
			crossA(speed);
			break;
		case 2:
			crossB(speed);
			break;
		case 3:
			crossC(speed);
			break;
		case 4:
			crossD(speed);
			break;
		}
		switch ( location )
		{
		case 0:
			_chassis.turn(-45);
			break;
		case 1:
			_chassis.turn(-30);
			break;
		case 2:
			break;
		case 3:
			_chassis.turn(30);
			break;
		case 4:
			_chassis.turn(45);
			break;
		}
		Shooter.getInstance().outtake(10);
	}
	
	public void crossLowBar(double speed)
	{
		
	}
	
	public void crossA(double speed)
	{
		
	}
	
	public void crossB(double speed)
	{
		
	}
	
	public void crossC(double speed)
	{
		
	}
	
	public void crossD(double speed)
	{
		
	}
}
