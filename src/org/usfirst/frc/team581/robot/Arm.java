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
	public double targetPositionRotations;
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
		/* choose to ensure sensor is positive when output is positive */
		tal0.setSensorPhase(Constants.kSensorPhase);
		
		//tal0.configForwardLimitSwitchSource(tal0, LimitSwitchNormal NormallyOpen, Constants.kTimeoutMs);
		tal0.configForwardSoftLimitThreshold(1000, 0);
		tal0.configReverseSoftLimitThreshold(-200, 0);
		tal0.configForwardSoftLimitEnable(true, 0);
		tal0.configReverseSoftLimitEnable(true, 0);

		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		tal0.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		
		/* set closed loop gains in slot0, typically kF stays zero. */
		tal0.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		tal0.config_kP(Constants.kPIDLoopIdx, 80.0, Constants.kTimeoutMs);
		tal0.config_kI(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		tal0.config_kD(Constants.kPIDLoopIdx, 0.1, Constants.kTimeoutMs);
		
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
	
	public void driveArm() {
		//armDrive.tankDrive(y, y);
		tal0.set(ControlMode.PercentOutput, Robot.oi.joy1.getY());
		pulseWidthPos = tal0.getSelectedSensorPosition(0);
		SmartDashboard.putString("DB/String 3", "" + pulseWidthPos);
	}
	
	public void setAngle(double angle) {
		//tal0.setSelectedSensorPosition(targetPositionRotations, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		targetPositionRotations = angle * (4096/360);
		pulseWidthPos = tal0.getSelectedSensorPosition(0);
		SmartDashboard.putString("DB/String 3", "" + pulseWidthPos);
		SmartDashboard.putString("DB/String 2", "" + targetPositionRotations);
		tal0.set(ControlMode.Position, targetPositionRotations);
		//tal0.setSelectedSensorPosition(targetPositionRotations, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}

}
