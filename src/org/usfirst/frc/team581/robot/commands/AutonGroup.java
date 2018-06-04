package org.usfirst.frc.team581.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonGroup extends CommandGroup{
	
	public AutonGroup(String side){
		if(side.length() > 0) {
			if(side.charAt(0) == 'L') {
				//left side switch
				addSequential(new AutonLeft(0.8, 0, 0, 36, 36));
				addParallel(new ArmAngle(1100));
				addSequential(new GrabEjectManual(false, 0.4));
			}else {
				//right side switch
				addSequential(new AutonRight(0.8, 0, 0, 36, 36));	
				addParallel(new ArmAngle(1100));
				addSequential(new GrabEjectManual(false, 0.4));
			}
		}
	}

}
