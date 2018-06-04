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
	public double INCHES_PER_PULSE = 1/ 18.9;        //1/13.08;
	public double kP = 0.005;
	public double kI = 0.0;
	public double kD = 0.001;
	public PIDController pidL;
	public PIDController pidR;
	final public double TOLERANCE = 2.0;
	private int state;

	public AutonDrive() {
		
        Robot.drive.m_right.setInverted(true);
		
        encR = Robot.drive.encR;
        encL = Robot.drive.encL;
        resetEncoders();
		
		encL.setDistancePerPulse(INCHES_PER_PULSE);
		encR.setDistancePerPulse(INCHES_PER_PULSE);
		encL.setPIDSourceType(PIDSourceType.kRate);
        encR.setPIDSourceType(PIDSourceType.kRate);
        
    	pidL = new PIDController(kP, kI, kD, encL, Robot.drive.m_left);
    	pidR = new PIDController(kP, kI, kD, encR, Robot.drive.m_right);
    	pidL.setAbsoluteTolerance(TOLERANCE);
    	pidR.setAbsoluteTolerance(TOLERANCE);
        stop();	
 
     }
	
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
	
	final private double MAX_ACCEL = 7.0;
	private double limitAcceleration(double currentVelocity, double futureVelocity) {
		double difference = futureVelocity - currentVelocity;
		if (Math.abs(difference) > MAX_ACCEL) {
			futureVelocity = currentVelocity + MAX_ACCEL * Math.signum(difference);			
		}
		return futureVelocity;
	}
	
	private void nextState() {
		this.resetEncoders();
		state++;
	}
	public double leftPulses, rightPulses;
	public void driveWithGamepad(double forwardVel, double turnVelL, double turnVelR, double leftTargetInches,  double rightTargetInches) {
		if (pidL == null || pidR == null) {
			//SmartDashboard.putString("DB/String 7", "Can't drive with null PIDs!");
		}
		
		final double leftInches = encL.getDistance();
		final double rightInches = encR.getDistance();
		final boolean isLeftDone = Math.abs(leftInches) > Math.abs(leftTargetInches);
		final boolean isRightDone = Math.abs(rightInches) > Math.abs(rightTargetInches);
		
		if (isLeftDone && isRightDone) {
			SmartDashboard.putString("DB/String 7", "Distance is reached");
			//pidL.setSetpoint(0);
			//pidR.setSetpoint(0);
			nextState();
		}
		
		forwardVel = scale(forwardVel);
		turnVelL = scale(turnVelL);
		turnVelR = scale(turnVelR);
		double maxInchesPerSecond = 72.0;
		double turningInchesPerSecond = 30.0;
		double targetInchesPerSecond = maxInchesPerSecond * forwardVel;
		SmartDashboard.putString("DB/String 4", "" + forwardVel);
		SmartDashboard.putString("DB/String 7", "" + turnVelL);

		double leftVelocity = limitAcceleration(pidL.getSetpoint(), targetInchesPerSecond + turnVelL * turningInchesPerSecond);
		double rghtVelocity = limitAcceleration(pidR.getSetpoint(), targetInchesPerSecond - turnVelR * turningInchesPerSecond);
		/*
		if (!isRightDone) {
	        rghtVelocity = rghtVelocity * Math.signum(rightTargetInches);
		}
		if (!isLeftDone) {
			leftVelocity = leftVelocity * Math.signum(leftTargetInches);
		}
		
		double difference = Math.abs(rightInches) - Math.abs(leftInches);
		rghtVelocity *= Math.exp(-difference);
		leftVelocity *= Math.exp(difference);
		*/
		
		SmartDashboard.putString("DB/String 0", "leftInches:" + leftInches);
		SmartDashboard.putString("DB/String 1", "leftVelocity:" + leftVelocity);
		SmartDashboard.putString("DB/String 5", "rightInches:" + rightInches);
		SmartDashboard.putString("DB/String 6", "rightVelocity:" + rghtVelocity);
		
		pidL.setSetpoint(leftVelocity);
		pidR.setSetpoint(rghtVelocity);
		
	}
	public void executeR(double forwardVel, double turnVelL, double turnVelR, double leftTargetInches,  double rightTargetInches) {
		switch(state) {
		case 0:
			this.driveWithGamepad(forwardVel, turnVelL, turnVelR, leftTargetInches, rightTargetInches);
			SmartDashboard.putString("DB/String 3", "Done 1");
			break;
		case 1:
			this.driveWithGamepad(0.8, 0.4, 0.8, 0, 43);
			SmartDashboard.putString("DB/String 8", "Done 2");
			break;
		case 2: 
			this.driveWithGamepad(forwardVel, turnVelL, turnVelR, 30, 30);
			break;
		case 3:
			this.driveWithGamepad(0, 0, -0.8, 0, 40);
			break;
		case 4:
			this.driveWithGamepad(forwardVel, turnVelL, turnVelR, leftTargetInches, rightTargetInches);
			break;
		case 5:
			this.driveWithGamepad(0, 0, 0, 0, 0);
			break;
		default:
		}
	}
	
	public void executeL(double forwardVel, double turnVelL, double turnVelR, double leftTargetInches,  double rightTargetInches) {
		switch(state) {
		case 0:
			this.driveWithGamepad(forwardVel, turnVelL, turnVelR, leftTargetInches, rightTargetInches);
			SmartDashboard.putString("DB/String 3", "Done 1");
			break;
		case 1:
			this.driveWithGamepad(0, 0, -0.8, 0, 40);
			SmartDashboard.putString("DB/String 8", "Done 2");
			break;
		case 2: 
			this.driveWithGamepad(forwardVel, turnVelL, turnVelR, 30, 30);
			break;
		case 3:
			this.driveWithGamepad(0, -0.8, 0, 40, 0);
			break;
		case 4:
			this.driveWithGamepad(forwardVel, turnVelL, turnVelR, leftTargetInches, rightTargetInches);
			break;
		case 5:
			this.driveWithGamepad(0, 0, 0, 0, 0);
			break;
		default:
		}
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
