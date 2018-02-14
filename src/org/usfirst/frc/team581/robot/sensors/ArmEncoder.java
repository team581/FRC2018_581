package org.usfirst.frc.team581.robot.sensors;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmEncoder implements PIDSource{

	private WPI_TalonSRX talon;
	private PIDSourceType pidSourceType;
	
	public ArmEncoder(WPI_TalonSRX talonArg) {
		talon = talonArg;
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		pidSourceType = PIDSourceType.kDisplacement;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSourceType = pidSource;		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		if (pidSourceType == PIDSourceType.kDisplacement) {
			int pos = talon.getSelectedSensorPosition(0);
			SmartDashboard.putString("DB/String 5", "" + Math.random());
			SmartDashboard.putString("DB/String 4", "" + pos);


			return (double)pos;
		}else {
			return talon.getSelectedSensorVelocity(0);
		}
	}

}
