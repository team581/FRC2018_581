package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmDrive extends Command{

	public ArmDrive(){
		// TODO Auto-generated constructor stub
		requires(Robot.arm);
	}
	protected void execute() {
		Robot.arm.testDrive();
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
