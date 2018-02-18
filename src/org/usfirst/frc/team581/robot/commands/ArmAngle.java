package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmAngle extends Command{

	public ArmAngle() {
		// TODO Auto-generated constructor stub
		requires(Robot.arm);
	}
	protected void execute() {
		Robot.arm.setAngle(90);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
