package org.usfirst.frc.team581.robot.sensors;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation;

public class NavX {
	protected AHRS ahrs;
	protected double mYawDegrees;
    protected double mYawRateDegreesPerSecond;
	public NavX() {
		try {

			/***********************************************************************

			 * navX-MXP:

			 * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.            

			 * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.

			 * 

			 * navX-Micro:

			 * - Communication via I2C (RoboRIO MXP or Onboard) and USB.

			 * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.

			 * 

			 * Multiple navX-model devices on a single robot are supported.

			 ************************************************************************/
            ahrs = new AHRS(SPI.Port.kMXP);
            //ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte)50);
            ahrs.enableLogging(true);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
        Timer.delay(1.0);
	}

}

