package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleCompressor extends Command{

	public ToggleCompressor() {
		// TODO Auto-generated constructor stub
	}
	protected void execute(){
    	Robot.grabber.toggleCompressor();
    }
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
