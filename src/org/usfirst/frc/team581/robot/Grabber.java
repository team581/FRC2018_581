package org.usfirst.frc.team581.robot.subsystems;

import org.usfirst.frc.team581.robot.RobotMap;
import org.usfirst.frc.team581.robot.commands.CompressorLoop;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Grabber extends Subsystem{
	Compressor compressor = new Compressor(0);
	Solenoid solenoid1 = new Solenoid(RobotMap.solenoid1);
	Solenoid solenoid2 = new Solenoid(RobotMap.solenoid2);
	boolean compressorEnable = false;
	
	Spark grabberMotor = new Spark(RobotMap.grabMotor);
	
	public Grabber() {
		// TODO Auto-generated constructor stub
	}
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new CompressorLoop());
	}
	public void solenoidOn(boolean sol) {
		if(sol) {
			solenoid1.set(true);
		}else {
			solenoid2.set(true);
		}
		
	}
	public void solenoidOff(boolean sol) {
		if(sol) {
			solenoid1.set(false);
		}else {
			solenoid2.set(false);
		}
	}
	public void toggleCompressor(){
		//System.out.println("--> Toggle boolean");
		compressorEnable = !compressorEnable;
	}
	
	public void compressorLoop(){
		if(compressorEnable) {
			compressor.start();
		}
		else {
			compressor.stop();
		}
	}
	
	public void grab(){
		grabberMotor.set(1.0);
	}
	public void eject() {
		grabberMotor.set(-1.0);
	}
	public void stopMotor() {
		grabberMotor.set(0);
	}

}
