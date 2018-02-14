package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonTest extends Command{
	double GET_R;
	double GET_L;
	
	private int state;
	public AutonTest() {
		// TODO Auto-generated constructor stub
		requires(Robot.drive);
		setTimeout(4.0);
		Robot.drive.encR.reset();
		Robot.drive.encL.reset();
		SmartDashboard.putString("DB/String 0", "" + Robot.drive.encL.get());
		SmartDashboard.putString("DB/String 1", "" + Robot.drive.encR.get());
		
		state = 0;

	}
	

	protected void execute() {	
		switch (state) {
	    case 0:
	    	driveForward(12*1);
	    	break;
	    case 10:
	    	waitForStop();
	    	break;
	    case 1:
	    	turn(90);
	    	break;
	    default: ; // do nothing
	    }
		
	}
	
	private void nextState() {
	  Robot.drive.encL.reset();
	  Robot.drive.encR.reset();
	  state++;
	}
	
	private void driveForward(double targetInches) {
		drive(targetInches, targetInches);
	}
	
	private void waitForStop() {
	  Robot.drive.drive(0.0, 0.0);
	  final double minRate = 0.5;
	  
	  double leftRate = Math.abs(Robot.drive.encL.getRate());
	  double rightRate = Math.abs(Robot.drive.encR.getRate());
	  
	  SmartDashboard.putString("DB/String 0", "" + leftRate);
	  SmartDashboard.putString("DB/String 1", "" + rightRate);
	  
	  if (leftRate < minRate && rightRate < minRate) {
		  SmartDashboard.putString("DB/String 2", "now stopped");
		  nextState();
	  }
	}
	
	final double INCHES_IN_ANGLE = 4.0;

	private void turn(double angle) {
		drive(INCHES_IN_ANGLE*angle, -INCHES_IN_ANGLE*angle);
	}
	
	private void drive(double leftTargetInches, double rightTargetInches) {
		final double leftInches = Robot.drive.encL.getDistance();
		final double rightInches = Robot.drive.encR.getDistance();
		
		final boolean isLeftDone = Math.abs(leftInches) > Math.abs(leftTargetInches);
		final boolean isRightDone = Math.abs(rightInches) > Math.abs(rightTargetInches);
		
		if (isLeftDone && isRightDone) {
			nextState();
			return;
		}
		
		double leftPower = 0.0;
		double rightPower = 0.0;


		
		double basePower = 0.5;
		if (Math.signum(leftTargetInches) != Math.signum(rightTargetInches)) {
			basePower = 0.45;
		}
		
		if (!isRightDone) {
           rightPower = basePower * Math.signum(rightTargetInches);
		}

		if (!isLeftDone) {
			leftPower = basePower * Math.signum(leftTargetInches);
		}
		
		double difference = Math.abs(rightInches) - Math.abs(leftInches);
		rightPower *= Math.exp(-difference);
		rightPower = clampToMotorRange(rightPower);
		leftPower *= Math.exp(difference);
		leftPower = clampToMotorRange(leftPower);
		
		SmartDashboard.putString("DB/String 0", "" + leftInches);
		SmartDashboard.putString("DB/String 1", "" + leftTargetInches);
		SmartDashboard.putString("DB/String 2", "" + leftPower);
		SmartDashboard.putString("DB/String 5", "" + rightInches);
		SmartDashboard.putString("DB/String 6", "" + rightTargetInches);
		SmartDashboard.putString("DB/String 7", "" + rightPower);


		Robot.drive.drive(leftPower, rightPower);
	}
	
	private double clampToMotorRange(double power) {
		final double minPower = 0.4;
		if (power < 0) {
			return clamp(-1.0, power, -minPower);
		}else {
			return clamp(minPower, power, 1.0);
		}
	}
	
	private double clamp(double lo, double x, double hi) {
	    return Math.min(Math.max(lo, x), hi);
	}
	
	

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
