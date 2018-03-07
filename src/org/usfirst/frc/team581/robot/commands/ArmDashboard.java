package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmDashboard extends Command{

	public ArmDashboard() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		Robot.arm.armDashboard();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
