package org.usfirst.frc.team581.robot.subsystems;

import org.usfirst.frc.team581.robot.Robot;
import org.usfirst.frc.team581.robot.RobotMap;
import org.usfirst.frc.team581.robot.commands.DriverDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Drive extends Subsystem{
	
	public DifferentialDrive mydrive;
	public Encoder encR;
	public Encoder encL;
	public Spark m_left = new Spark(RobotMap.leftMotor);
	public Spark m_right = new Spark(RobotMap.rightMotor);
	public double INCHES_PER_PULSE = 1/18.9;
	
	public Drive() {
		mydrive = new DifferentialDrive(m_left, m_right);
		encR = new Encoder(0,1,false, Encoder.EncodingType.k4X);
		encL = new Encoder(2,3,true, Encoder.EncodingType.k4X); 
		//encL.setPIDSourceType(PIDSourceType.kDisplacement);
       //encR.setPIDSourceType(PIDSourceType.kDisplacement);
		//encR.setMaxPeriod(0.1);
		//encL.setMaxPeriod(0.1);
		//encR.setMinRate(10);
		//encL.setMinRate(10);
		encR.reset();
		encL.reset();
		encR.setDistancePerPulse(INCHES_PER_PULSE);
		encL.setDistancePerPulse(INCHES_PER_PULSE);
	}

	@Override
	protected void initDefaultCommand() {
      setDefaultCommand(new DriverDrive());		
	}
	/*
	public ITable getTable() {

        NetworkTable driveTable;

        driveTable = NetworkTable.getTable("Drivetrain");

        driveTable.putNumber("Left PWM", leftMotor.get());

        driveTable.putNumber("Right PWM", rightMotor.get());

        driveTable.putNumber("Left Speed (inch per second)", leftEncoder.getRate());

        driveTable.putNumber("Right Speed (inch per second)", rightEncoder.getRate());

        driveTable.putNumber("Left Distance (inches)", leftEncoder.getDistance());

        driveTable.putNumber("Right Distance (inches)", rightEncoder.getDistance());

        SmartDashboard.putData("Drivetrain PID Controller", arcadeController);

        return driveTable;

    }
    */
	
    final double startMoving = 0.45;
	
	private double scale(double x) {
		return (Math.signum(x) * scaleHelper(Math.abs(x)))/2;
	}
	
	private double scaleHelper(double x) {
		/*
		if (x < 0.01) {
			return 0.0;
		}else if (x > 0.9) {
			return 1.0;
		}else {
		*/
		    return Math.min(1.0, Math.pow(2, x) - 1);
	}
	private double smartR(double x, double y) {
		double right = x - y/2;
		return right;
	}
	private double smartL(double x, double y) {
		double left = x + y/2;
		return left;
	}
	public double leftPulses, rightPulses;
	public void driveWithGamepad(double xspeed, double yspeed) {
		leftPulses = Robot.drive.encR.get();
		rightPulses = Robot.drive.encL.get();
		mydrive.tankDrive(smartL(xspeed, yspeed), smartR(xspeed, yspeed));
		SmartDashboard.putString("DB/String 0", "" + leftPulses);
		SmartDashboard.putString("DB/String 1", "" + rightPulses);
		
	}
	public void drive(double xspeed, double yspeed) {
		mydrive.tankDrive(xspeed, yspeed);
	}
	
	

}
