// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  PWMVictorSPX left1 = new PWMVictorSPX(Constants.MOTOR_PORT_LEFT1);
  PWMVictorSPX left2 = new PWMVictorSPX(Constants.MOTOR_PORT_LEFT2);
  PWMVictorSPX right1 = new PWMVictorSPX(Constants.MOTOR_PORT_RIGHT1);
  PWMVictorSPX right2 = new PWMVictorSPX(Constants.MOTOR_PORT_RIGHT2);

  public void assignMotorSpeeds(double leftSpeed, double rightSpeed) {
    left1.set(leftSpeed);
    left2.set(leftSpeed);
    right1.set(rightSpeed);
    right2.set(rightSpeed);
  }

  public DriveTrain() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
