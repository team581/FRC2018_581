/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team581.robot;

import org.usfirst.frc.team581.robot.commands.GrabEject;
import org.usfirst.frc.team581.robot.commands.GrabStop;
import org.usfirst.frc.team581.robot.commands.SolenoidOff;
import org.usfirst.frc.team581.robot.commands.SolenoidOn;
import org.usfirst.frc.team581.robot.commands.ToggleCompressor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick gamepad = new Joystick(3);
	public Joystick joy1 = new Joystick(0);
	
	Button b10 = new JoystickButton(joy1, 10);
	Button rb = new JoystickButton(gamepad, 6); //angle up
	Button rt = new JoystickButton(gamepad, 8); //angle down
	Button x = new JoystickButton(gamepad, 1);  //latch on
	Button a = new JoystickButton(gamepad, 2);  //let go off tote
	Button lb = new JoystickButton(gamepad, 5); //grab in
	Button lt = new JoystickButton(gamepad, 7);//eject
	
	private double lastLeftY = -2;
	
	private double lastRightY = -2;
	
	public double getGamepadLeftX() {
		return gamepad.getX();
	}
	public double getGamepadLeftY() {
		//makes the robot stutter
		return -gamepad.getY();
	}
	public double getGamepadRightX() {
		return gamepad.getZ();
	}
	public double getGamepadRightY() {
		return -gamepad.getThrottle();
	}
	public double getJoy1Y() {
		return joy1.getY();
	}
	public double getJoy1Z() {
		return joy1.getZ();
	}
	public OI() {
		b10.whenPressed(new ToggleCompressor());
		rb.whenPressed(new SolenoidOn(true));
		rt.whenPressed(new SolenoidOff(true));
		x.whenPressed(new SolenoidOn(false));
		a.whenPressed(new SolenoidOff(false));
		lb.whenPressed(new GrabEject(true));
		lt.whenPressed(new GrabEject(false));
		lb.whenReleased(new GrabStop());
		lt.whenReleased(new GrabStop());
	}
}
