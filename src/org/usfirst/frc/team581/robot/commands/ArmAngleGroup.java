package org.usfirst.frc.team581.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmAngleGroup extends CommandGroup{

	public ArmAngleGroup(int angle) {
		// TODO Auto-generated constructor stub
		addParallel(new ArmAngle(angle));
		addParallel(new ArmDashboard());
		
	}

}
