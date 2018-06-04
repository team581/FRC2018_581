package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabEject extends Command{
	public GrabEject() {
		// TODO Auto-generated constructor stub
	}
	protected void execute() {
		/*
		if(grabEj) {
			Robot.grabber.grab(Power);
		}else {
			Robot.grabber.eject(Power);
		}
		*/
		Robot.grabber.grabPower(Robot.oi.getGamepad1LeftY(), Robot.oi.getGamepad1RightX());
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
