package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class AutonRight extends Command{
	public double forwardVel1;
	public double turnVelL1;
	public double turnVelR1;
	public double leftTargetInches1;
	public double rightTargetInches1;
	public AutonRight(double forwardVel, double turnVelL, double turnVelR, double leftTargetInches,  double rightTargetInches) {
		requires(Robot.autondrive);
		Robot.autondrive.resetEncoders();
		forwardVel1 = forwardVel;
		turnVelL1 = turnVelL;
		turnVelR1 = turnVelR;
		leftTargetInches1 = leftTargetInches;
		rightTargetInches1 = rightTargetInches;
	}
	protected void execute() {
		Robot.autondrive.executeR(forwardVel1, turnVelL1, turnVelR1, leftTargetInches1, rightTargetInches1);
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
