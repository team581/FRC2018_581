package org.usfirst.frc.team581.robot.commands;

import org.usfirst.frc.team581.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonTest extends Command{
	double DISTANCE_PER_PULSE_R;
	double DISTANCE_PER_PULSE_L;
	double GET_R;
	double GET_L;
	public AutonTest() {
		// TODO Auto-generated constructor stub
		requires(Robot.drive);
		setTimeout(4.0);
	}
	

	protected void execute() {
		double leftPulses, rightPulses;
		
		Robot.drive.drive(0.5, 0.5);
		leftPulses = Robot.drive.encR.get();
		rightPulses = Robot.drive.encL.get();
		//SmartDashboard.putString("DB/String 0", "0");
		if (leftPulses < 360) {
			SmartDashboard.putString("DB/String 1", "" + leftPulses);
			SmartDashboard.putString("DB/String 2", "" + rightPulses);
		}
		else {	
			Robot.drive.drive(0.0, 0.0);
		}
		/*
		DISTANCE_PER_PULSE_L = Robot.drive.encL.getDistancePerPulse();
		GET_R = Robot.drive.encR.get();
		GET_L = Robot.drive.encL.get();
		
		System.out.println(DISTANCE_PER_PULSE_R + " " + DISTANCE_PER_PULSE_L + " " + GET_R + " " + GET_L);
		*/
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
