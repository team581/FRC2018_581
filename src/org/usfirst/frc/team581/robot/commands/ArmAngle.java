package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmAngle extends Command{
	public int Angle;
	public ArmAngle(int angle) {
		// TODO Auto-generated constructor stub
		requires(Robot.arm);
		Angle = angle;
	}
	protected void execute() {
		Robot.arm.setAngle(Angle);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
