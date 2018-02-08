package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class AutonRight extends Command{
	public double speed;
	public AutonRight(double d_speed, double time) {
		requires(Robot.drive);
		setTimeout(time);
		speed = d_speed;
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		String gameData;
		double xspeed = speed;
		double yspeed = speed;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L')
		{
			Robot.drive.driveWithGamepad(xspeed, yspeed);
		} else {
			Robot.drive.driveWithGamepad(-xspeed, -yspeed);
		}
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

}
