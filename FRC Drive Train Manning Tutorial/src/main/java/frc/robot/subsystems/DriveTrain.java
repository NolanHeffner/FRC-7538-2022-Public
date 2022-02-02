// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  WPI_TalonFX left1 = new WPI_TalonFX(Constants.LEFT_1_PORT);
  WPI_TalonFX left2 = new WPI_TalonFX(Constants.LEFT_2_PORT);
  WPI_TalonFX right1 = new WPI_TalonFX(Constants.RIGHT_1_PORT);
  WPI_TalonFX right2 = new WPI_TalonFX(Constants.RIGHT_2_PORT);

  public DriveTrain() {
    invertLeftMotors();
  }

  public void invertLeftMotors() {
    left1.setInverted(true);
    left2.setInverted(true);
  }

  public void setLeftMotors(double speed) {
    left1.set(speed);
    left2.set(speed);
  }

  public void setRightMotors(double speed) {
    right1.set(speed);
    right2.set(speed);
  }

  public void stopMotors() {
    left1.set(0);
    left2.set(0);
    right1.set(0);
    right2.set(0);
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
