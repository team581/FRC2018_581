package org.usfirst.frc.team581.robot.subsystems;

import org.usfirst.frc.team581.robot.Robot;
import org.usfirst.frc.team581.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonDrive extends Subsystem{
	
	public Encoder encR;
	public Encoder encL;
	public Spark m_left = new Spark(RobotMap.leftMotor);
	public Spark m_right = new Spark(RobotMap.rightMotor);
	public double INCHES_PER_PULSE = 1/ 18.9;        //1/13.08;
	public double kP = 0.005;
	public double kI = 0.0;
	public double kD = 0.001;
	public PIDController pidL;
	public PIDController pidR;
	final public double TOLERANCE = 2.0;

	public AutonDrive() {
		/*
        m_right.setInverted(true);
		
		encR = new Encoder(2,3, false, Encoder.EncodingType.k4X);
		encL = new Encoder(0,1, true, Encoder.EncodingType.k4X); 
        resetEncoders();
		
		encL.setDistancePerPulse(INCHES_PER_PULSE);
		encR.setDistancePerPulse(INCHES_PER_PULSE);
		encL.setPIDSourceType(PIDSourceType.kRate);
        encR.setPIDSourceType(PIDSourceType.kRate);
        
    	pidL = new PIDController(kP, kI, kD, encL, m_left);
    	pidR = new PIDController(kP, kI, kD, encR, m_right);
    	pidL.setAbsoluteTolerance(TOLERANCE);
    	pidR.setAbsoluteTolerance(TOLERANCE);
        stop();	
        */
     }
	/*
	public void start() {
        pidL.enable();
        pidR.enable();
	}

	public void stop() {
		pidL.disable();
		pidR.disable();
	}
	
	public void resetEncoders(){
		encR.reset();
		encL.reset();
	}
	
	private double scale(double x) {
		return Math.signum(x) * scaleHelper(Math.abs(x));
	}
	
	private double scaleHelper(double x) {
		    return Math.min(1.0, Math.pow(2, x) - 1);
	}
	
	final private double MAX_ACCEL = 4.0;
	private double limitAcceleration(double currentVelocity, double futureVelocity) {
		double difference = futureVelocity - currentVelocity;
		if (Math.abs(difference) > MAX_ACCEL) {
			futureVelocity = currentVelocity + MAX_ACCEL * Math.signum(difference);			
		}
		return futureVelocity;
	}
	
	public double leftPulses, rightPulses;
	public void driveWithGamepad(double forwardVel, double turnVel, double leftTargetInches,  double rightTargetInches) {
		if (pidL == null || pidR == null) {
			SmartDashboard.putString("DB/String 7", "Can't drive with null PIDs!");
			return;
		}
		
		final double leftInches = encL.getDistance();
		final double rightInches = encR.getDistance();
		final boolean isLeftDone = Math.abs(leftInches) > Math.abs(leftTargetInches);
		final boolean isRightDone = Math.abs(rightInches) > Math.abs(rightTargetInches);
		
		if (isLeftDone && isRightDone) {
			 pidL.setSetpoint(0);
		     pidR.setSetpoint(0);
		}
		
		forwardVel = scale(forwardVel);
		turnVel = scale(turnVel);

		//double maxInchesPerSecond = 72.0;
		//double turningInchesPerSecond = 30.0;
		//double targetInchesPerSecond = forwardVel;
		//SmartDashboard.putString("DB/String 4", "" + targetInchesPerSecond);

		double leftVelocity = limitAcceleration(pidL.getSetpoint(), forwardVel + turnVel);
		double rghtVelocity = limitAcceleration(pidR.getSetpoint(), forwardVel - turnVel);
		
		if (!isRightDone) {
	           rghtVelocity = rghtVelocity * Math.signum(rightTargetInches);
		}
		if (!isLeftDone) {
			leftVelocity = leftVelocity * Math.signum(leftTargetInches);
		}
		
		double difference = Math.abs(rightInches) - Math.abs(leftInches);
		rghtVelocity *= Math.exp(-difference);
		leftVelocity *= Math.exp(difference);
        pidL.setSetpoint(leftVelocity);
        pidR.setSetpoint(rghtVelocity);
		
	}
	*/
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
