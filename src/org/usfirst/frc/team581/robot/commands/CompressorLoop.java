package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CompressorLoop extends Command{

	public CompressorLoop() {
		// TODO Auto-generated constructor stub
		requires(Robot.grabber);
	}
	protected void execute() {
		Robot.grabber.compressorLoop();
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
