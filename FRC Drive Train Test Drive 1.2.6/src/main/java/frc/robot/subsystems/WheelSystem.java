// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WheelSystem extends SubsystemBase {

  // Instantiates wheels to using port maps to connect code to Roborio

  WPI_VictorSPX intakeWheel = new WPI_VictorSPX(Constants.INTAKE_WHEEL_CAN_ID);
  WPI_VictorSPX shooterWheel = new WPI_VictorSPX(Constants.SHOOTER_WHEEL_CAN_ID);

  public WheelSystem() {
    intakeWheel.setInverted(true);
  }

  // Pushes new speed to intake wheel motor
  public void setIntakeWheelSpeed(double speed) {
    // Updates intake wheel speed on dashboard
    SmartDashboard.putNumber("Intake Speed: ", speed);
    intakeWheel.set(speed);
  }

  // Pushes new speed to intake wheel motor
  public void setShooterWheelSpeed(double speed) {
    // Updates shooter wheel speed on dashboard
    SmartDashboard.putNumber("Shooter Speed: ", speed);
    shooterWheel.set(speed);
  }
}
