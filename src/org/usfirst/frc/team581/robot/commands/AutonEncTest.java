package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutonEncTest extends Command{

	public AutonEncTest() {
		// TODO Auto-generated constructor stub
		requires(Robot.autondrive);
		Robot.autondrive.resetEncoders();
	}
	protected void execute() {	
		Robot.autondrive.executeR(0.8, 0, 0, 36, 36);
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
