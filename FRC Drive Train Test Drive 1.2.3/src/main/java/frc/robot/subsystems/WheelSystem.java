// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WheelSystem extends SubsystemBase {

  // Instantiates wheels to using port maps to connect code to Roborio
  PWMVictorSPX intakeWheel = new PWMVictorSPX(Constants.INTAKE_WHEEL_PORT);
  PWMVictorSPX shooterWheel = new PWMVictorSPX(Constants.SHOOTER_WHEEL_PORT);

  // Pushes new speed to intake wheel motor
  public void setIntakeWheelSpeed(double speed) {
    // Updates intake wheel speed on dashboard
    SmartDashboard.putNumber("Intake Speed", speed);
    intakeWheel.set(speed);
  }

  // Pushes new speed to intake wheel motor
  public void setShooterWheelSpeed(double speed) {
    // Updates intake wheel speed on dashboard
    SmartDashboard.putNumber("Shooter Speed", speed);
    shooterWheel.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
