// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import frc.robot.Constants;

public class WheelSystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  PWMVictorSPX Low = new PWMVictorSPX(Constants.MOTOR_PORT_INTAKE);
  PWMVictorSPX High = new PWMVictorSPX(Constants.MOTOR_PORT_OUTPUT);

  public void assignIntakeSpeed(double lowSpeed) {
    Low.set(lowSpeed);
  }

  public void assignOutputSpeed(double highSpeed) {
    High.set(highSpeed);
  }
  
  public WheelSystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
