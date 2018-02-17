package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SolenoidOff extends Command{
	boolean sol;
	public SolenoidOff(boolean solenoid) {
		// TODO Auto-generated constructor stub
		requires(Robot.grabber);
		sol = solenoid;
	}
	protected void execute() {
		Robot.grabber.solenoidOff(sol);
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
