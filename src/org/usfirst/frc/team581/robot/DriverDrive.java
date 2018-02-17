package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriverDrive extends Command{
	public DriverDrive() {
		requires(Robot.drive);
	}
	protected void execute() {	
		Robot.drive.driveWithGamepad(Robot.oi.getGamepadLeftY(), Robot.oi.getGamepadRightX());
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
