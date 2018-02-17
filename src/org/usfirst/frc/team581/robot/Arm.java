package org.usfirst.frc.team581.robot.subsystems;


import org.usfirst.frc.team581.robot.commands.ArmDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Arm extends Subsystem{
	public DifferentialDrive armDrive;
	public WPI_TalonSRX tal0 = new WPI_TalonSRX(0);
	public WPI_TalonSRX tal1 = new WPI_TalonSRX(1);

	public Arm() {
		armDrive = new DifferentialDrive(tal0, tal1);
	}
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ArmDrive());
	}
	public void driveArm(double y) {
		armDrive.tankDrive(y, y);
	}

}
