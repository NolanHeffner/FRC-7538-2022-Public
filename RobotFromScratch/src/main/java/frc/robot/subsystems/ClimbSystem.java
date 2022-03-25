// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

import frc.robot.Constants;

public class ClimbSystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  PWMVictorSPX Climb1 = new PWMVictorSPX(Constants.MOTOR_PORT_CLIMB1);
  PWMVictorSPX Climb2 = new PWMVictorSPX(Constants.MOTOR_PORT_CLIMB2);
  
  public void assignExtendSpeeds(double Climb1Speed) {
    Climb1.set(Climb1Speed);
  }

  public void assignExtend2Speeds(double Climb2Speed) {
    Climb2.set(Climb2Speed);
  }

  public ClimbSystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
