package org.usfirst.frc.team581.robot.subsystems;

import org.usfirst.frc.team581.robot.Robot;
import org.usfirst.frc.team581.robot.RobotMap;
import org.usfirst.frc.team581.robot.commands.DriverDrive;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class Drive extends Subsystem{
	
	//public DifferentialDrive mydrive;
	public Encoder encR;
	public Encoder encL;
	public Spark m_left = new Spark(RobotMap.leftMotor);
	public Spark m_right = new Spark(RobotMap.rightMotor);
	public double INCHES_PER_PULSE = 1/18.9;
	public double kP = 0.01;
	public double kI = 0.0;
	public double kD = 0.001;
	public PIDController pidL;
	public PIDController pidR;
	final public double TOLERANCE = 2.0;

	
	public Drive() {
		//mydrive = new DifferentialDrive(m_left, m_right);
		
        m_right.setInverted(true);
		
		encR = new Encoder(2,3, false, Encoder.EncodingType.k4X);
		encL = new Encoder(0,1, true, Encoder.EncodingType.k4X); 
		encR.reset();
		encL.reset();
		encL.setDistancePerPulse(INCHES_PER_PULSE);
		encR.setDistancePerPulse(INCHES_PER_PULSE);
		encL.setPIDSourceType(PIDSourceType.kRate);
        encR.setPIDSourceType(PIDSourceType.kRate);
        
    	pidL = new PIDController(kP, kI, kD, encL, m_left);
    	pidR = new PIDController(kP, kI, kD, encR, m_right);
    	pidL.setAbsoluteTolerance(TOLERANCE);
    	pidR.setAbsoluteTolerance(TOLERANCE);
		
        pidL.enable();
        pidR.enable();

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
		return Math.signum(x) * scaleHelper(Math.abs(x));
	}
	
	private double scaleHelper(double x) {
		    return Math.min(1.0, Math.pow(2, x) - 1);
	}
	
	final private double MAX_ACCEL = 3.0;
	private double limitAcceleration(double currentVelocity, double futureVelocity) {
		double difference = futureVelocity - currentVelocity;
		if (Math.abs(difference) > MAX_ACCEL) {
			futureVelocity = currentVelocity + MAX_ACCEL * Math.signum(difference);			
		}
		return futureVelocity;
	}
	
	public double leftPulses, rightPulses;
	public void driveWithGamepad(double forwardInput, double turnInput) {
		forwardInput = scale(forwardInput);
		turnInput = scale(turnInput);
		
		double leftRate = Robot.drive.encR.getRate();
		double rightRate = Robot.drive.encL.getRate();
		SmartDashboard.putString("DB/String 5", "" + leftRate);
		SmartDashboard.putString("DB/String 0", "" + rightRate);
		SmartDashboard.putString("DB/String 1", "turnInput");
		SmartDashboard.putString("DB/String 2", ""+turnInput);
		SmartDashboard.putString("DB/String 6", "forwardInput");
		SmartDashboard.putString("DB/String 7", ""+forwardInput);

		double maxInchesPerSecond = 72.0;
		double turningInchesPerSecond = 50.0;
		double targetInchesPerSecond = maxInchesPerSecond * forwardInput;
		SmartDashboard.putString("DB/String 4", "" + targetInchesPerSecond);

		double leftVelocity = limitAcceleration(pidL.getSetpoint(), targetInchesPerSecond + turnInput * turningInchesPerSecond);
		double rghtVelocity = limitAcceleration(pidR.getSetpoint(), targetInchesPerSecond - turnInput * turningInchesPerSecond);

        pidL.setSetpoint(leftVelocity);
        pidR.setSetpoint(rghtVelocity);
		
	}
	public void drive(double xspeed, double yspeed) {
		//mydrive.tankDrive(xspeed, yspeed);
	}
	
	

}
