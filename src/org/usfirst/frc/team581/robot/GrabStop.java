package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabStop extends Command{

	public GrabStop() {
		// TODO Auto-generated constructor stub
	}
	
	protected void execute() {
		Robot.grabber.stopMotor();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
