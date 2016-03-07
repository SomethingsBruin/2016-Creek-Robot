
package org.usfirst.frc.team4550.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.NIVision.RotatedRect;
import com.ni.vision.NIVision.ShapeMode;

import mechanism.Arm;
import mechanism.Shooter;
import Chassis.Chassis;
import edu.wpi.first.wpilibj.CameraServer;
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
	private int autoSelected;
	private int defenseSelected;
	private SendableChooser _chooser;
	private SendableChooser _chooser2;
	private Driver _driver;

	private Chassis _chassis;
	private Arm _arm;
	private Shooter _shooter;

	private boolean _testRun;
	private boolean _autonomous;

	private int _session;
    private Image _frame;
	
    Rect _rect;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		_chooser = new SendableChooser();
		_chooser2 = new SendableChooser();

		_chooser.addObject( "Far Left", 0 );
		_chooser.addObject( "Left", 1 );
		_chooser.addObject( "Middle", 2 );
		_chooser.addObject( "Right", 3 );
		_chooser.addObject( "Far Right", 4 );
		_chooser.addObject( "Do Nothing", 5 );
		
		_chooser2.addObject( "Low Bar", 0 );
		_chooser2.addObject( "Ramparts", 1 );
		_chooser2.addObject( "Rough Terrain", 2 );
		_chooser2.addObject( "Moat", 3 );
		_chooser2.addObject( "Rock Wall", 4 );
		_chooser2.addObject( "Do nothing", 5 );
		
		SmartDashboard.putData( "Auto Positions", _chooser );
		SmartDashboard.putData("Auto Obstacles", _chooser2);

		_driver = Driver.getInstance();
		_chassis = Chassis.getChassis();
		_arm = Arm.getArm();
		_shooter = Shooter.getInstance();
		_testRun = false;
		_autonomous = false;
		
		_frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		_session = NIVision.IMAQdxOpenCamera( "cam3" , NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(_session);
	
        NIVision.IMAQdxStartAcquisition( _session);

        
        _rect = new NIVision.Rect(10, 10, 100, 100); 
        
	}

	/**
	 * This autonomous (along with the _chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable _chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the _chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the Sendable_chooser make sure to add them to the _chooser code above as well.
	 */
	public void autonomousInit() {
		//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		autoSelected = (int)_chooser.getSelected( );
		defenseSelected = (int) _chooser2.getSelected( );
		System.out.println("Auto selected: " + autoSelected);
		_autonomous = false;
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		_chassis.reset();
		if(!_autonomous)
		{
			double drive1 = 185;
			
			Timer.delay(1);

			switch(defenseSelected)
			{
			case 0:
				//armMove = true;
				break;
			case 1:
				break;
			case 2:
				break;
			case 3: 
				break;
			case 4: 
				break;
			default:
				drive1 = 0;
				break;
			}
			_chassis.move(drive1, -0.5);
			
			_autonomous = true;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		NIVision.IMAQdxGrab(_session, _frame, 1);
		//NIVision.imaqDrawShapeOnImage(_frame, _frame, _rect, DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 100.0f);
		NIVision.imaqDrawLineOnImage(_frame, _frame, DrawMode.DRAW_VALUE, new Point(325, 415), new Point(375, 416) , 100.0f);
		NIVision.imaqDrawLineOnImage(_frame, _frame, DrawMode.DRAW_VALUE, new Point(335, 435), new Point(336, 386) , 100.0f);
		CameraServer.getInstance().setImage(_frame);
		
		_chassis.drive( Utilities.exp( Utilities.fixInput( _driver.getAxis(RobotMap.LEFT_Y) ), 5 ), Utilities.exp( Utilities.fixInput( _driver.getAxis(RobotMap.LEFT_X) ), 5 ) );
		//_chassis.drive(Utilities.exp(_driver.getAxis(RobotMap.LEFT_Y), 3), Utilities.exp(_driver.getAxis(RobotMap.LEFT_X),3) );

		if( Math.abs( _driver.getAxis(RobotMap.LEFT_Y) ) < .05 && Math.abs(  _driver.getAxis(RobotMap.LEFT_X) ) < .05 && Math.abs( _driver.getAxis(RobotMap.RIGHT_X) ) > .05  )
		{
			System.out.println( 0 );
			_chassis.drive(0, Utilities.exp( _driver.getAxis(RobotMap.RIGHT_X) / 1.75, 3 ) );
		}
		
		//Right trigger to move up 
		if( _driver.getAxis(RobotMap.RIGHT_2) > .03 )
		{
			//System.out.println( Utilities.exp(-1.0 * _driver.getAxis(RobotMap.RIGHT_2), 5) );
			_arm.move( Utilities.exp(-1.0 * _driver.getAxis(RobotMap.RIGHT_2), 5));
		}
		//Left trigger to move down
		else if( _driver.getAxis(RobotMap.LEFT_2) > 0.03 )
		{
			//System.out.println(Utilities.exp(_driver.getAxis(RobotMap.LEFT_2),5) );
			_arm.move(Utilities.exp(_driver.getAxis(RobotMap.LEFT_2),5));
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
		else if( _driver.getButton( RobotMap.Y_BUTTON ) )
		{
			_shooter.outtake( 0.5 );
		}
		//Intake if L1 Pressed 
		else if( _driver.getButton(RobotMap.LB_BUTTON ) )
		{
			_shooter.intake(.6);
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
		_driver.displayStats();
		
//		if( !_testRun )
//		{
//			_chassis.turnToAngle(90, .5);
//			Timer.delay(.5);
//			_chassis.move( 156 , -.5);
//			Timer.delay(.5);
//			_chassis.reset();
//			_chassis.turnToAngle(-90, .5);
//			Timer.delay(.5);
//			_chassis.move(24, -.5);
//			_testRun = true;
//
//		}
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
