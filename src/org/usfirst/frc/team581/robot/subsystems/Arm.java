package org.usfirst.frc.team581.robot.subsystems;


import org.usfirst.frc.team581.robot.Robot;
import org.usfirst.frc.team581.robot.commands.ArmDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem{
	public DifferentialDrive armDrive;
	public TalonSRX tal0 = new TalonSRX(0);
	public TalonSRX tal1 = new TalonSRX(1);
	public int pulseWidthPos;
	//public SpeedControllerGroup talonGroup;
	//public PIDController pidController;
	//public ArmEncoder encoder;
	//public double zero;

	public Arm() {
		//armDrive = new DifferentialDrive(tal0, tal1);
		tal1.follow(tal0);
		tal0.setInverted(true);
		tal0.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,0);
		tal0.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		
		double targetPositionRotations = tal0.getSelectedSensorPosition(0);
		tal0.set(ControlMode.Position, targetPositionRotations);
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ArmDrive());
	}
	double velocity;
	public void testDrive () {
		if (Robot.oi.joy1.getRawButton(1)) {
			tal0.set(ControlMode.PercentOutput, 0.25); /* 25 % output */
			velocity = tal0.getSelectedSensorVelocity(0);
		}
		else 
			tal0.set(ControlMode.PercentOutput, 0.0);
		
		SmartDashboard.putString("DB/String 3", "" + velocity);
		
	}
	
	public void driveArm(double y) {
		//armDrive.tankDrive(y, y);
		pulseWidthPos = tal0.getSelectedSensorPosition(0);
		SmartDashboard.putString("DB/String 3", "" + pulseWidthPos);
	}

}
