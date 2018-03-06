package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabEject extends Command{
	boolean grabEj;
	double Power;
	public GrabEject(boolean ge, double power) {
		// TODO Auto-generated constructor stub
		grabEj = ge;
		Power = power;
	}
	protected void execute() {
		if(grabEj) {
			Robot.grabber.grab(Power);
		}else {
			Robot.grabber.eject(Power);
		}
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
