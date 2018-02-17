package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GrabEject extends Command{
	boolean grabEj;
	public GrabEject(boolean ge) {
		// TODO Auto-generated constructor stub
		grabEj = ge;
	}
	protected void execute() {
		if(grabEj) {
			Robot.grabber.grab();
		}else {
			Robot.grabber.eject();
		}
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
