package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutonEncTest extends Command{

	public AutonEncTest() {
		// TODO Auto-generated constructor stub
		requires(Robot.autondrive);
	}
	protected void execute() {	
		//Robot.autondrive.driveWithGamepad(0.5, 0, 36, 36);
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
