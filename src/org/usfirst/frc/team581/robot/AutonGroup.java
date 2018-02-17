package org.usfirst.frc.team581.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGroup extends CommandGroup{
	
	public AutonGroup(String side){
		if(side.charAt(0) == 'L') {
			addSequential(new AutonLeft());
		}else if(side.charAt(0) == 'R') {
			addSequential(new AutonRight(0.20, 2.0));
		}else {
			addSequential(new AutonMid());		
			}
	}

}
