
package org.usfirst.frc.team4550.robot;

import mechanism.Arm;
import mechanism.Shooter;
import Chassis.Chassis;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	private Driver _driver;
	
	private Chassis _chassis;
    private Arm _arm;
	private Shooter _shooter;
	
	private boolean _testRun;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        _driver = Driver.getInstance();
        _chassis = Chassis.getChassis();
        _arm = Arm.getArm();
        _shooter = Shooter.getInstance();
        _testRun = false;
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	_chassis.drive( Utilities.exp( Utilities.fixInput( _driver.getAxis(RobotMap.LEFT_Y) ), 5 ), Utilities.exp( Utilities.fixInput( _driver.getAxis(RobotMap.LEFT_X) ), 5 ) );
    	//_chassis.drive(Utilities.exp(_driver.getAxis(RobotMap.LEFT_Y), 3), Utilities.exp(_driver.getAxis(RobotMap.LEFT_X),3) );
    	
    	//Right trigger to move up 
    	if( _driver.getAxis(RobotMap.RIGHT_2) > .15 )
    	{
    		_arm.move( Utilities.exp(-1.0 * _driver.getAxis(RobotMap.RIGHT_2), 5));
    	}
    	//Left trigger to move down
    	else if( _driver.getAxis(RobotMap.LEFT_2) > .15 )
    	{
    		_arm.move(Utilities.exp(_driver.getAxis(RobotMap.LEFT_2),3));
    	}
    	else
    	{
    		_arm.stop();
    	}
    	
    	//Press "A" to shoot
    	if( _driver.getButton(RobotMap.A_BUTTON))
    	{
    		_shooter.outtake( 1.0 );
    	} 
    	//Intake if L1 Pressed 
    	else if( _driver.getButton(RobotMap.LB_BUTTON ) )
    	{
    		_shooter.intake(.4);
    	}
    	else
    	{
    		_shooter.stop();
    	}
    }
    
    /**
     * This function is called once before test is initialized 
     */
    public void testInit()
    {
    	_chassis.reset();
    	_testRun = false;
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {
    	if( !_testRun )
    	{
    		_chassis.turnToAngle(90, .5);
    		Timer.delay(.5);
    		_chassis.move( 156 , -.5);
    		Timer.delay(.5);
    		_chassis.reset();
    		_chassis.turnToAngle(-90, .5);
    		Timer.delay(.5);
    		_chassis.move(24, -.5);
    		_testRun = true;
    		
    	}
    	//System.out.println( _chassis.getAngle() );
    	//System.out.println( _arm.getLowerSwitch() + " " + _arm.getUpperSwitch() );
    	//Timer.delay(.5);
    	/*if( !_testRun )
    	{
    		_chassis.move( 82, -.55 );
    		_testRun = true;
    	}*/
    	/*if( !_testRun )
    	{
    		_chassis.drive( -.55, 0 );
    		Timer.delay(2);
    		_chassis.stop();
    		_testRun = true;
    	}
    	System.out.println( "LEFT: " + _chassis.getLeftEncoder() + " RIGHT: " + _chassis.getRightEncoder() );*/
    }
    
}
