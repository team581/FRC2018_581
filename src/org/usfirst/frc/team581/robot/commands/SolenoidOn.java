package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

public class SolenoidOn extends Command{
	boolean solen;
	public SolenoidOn(boolean solenoid) {
		// TODO Auto-generated constructor stub
		requires(Robot.grabber);
		solen = solenoid;
	}
	protected void execute() {
		Robot.grabber.solenoidOn(solen);
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
