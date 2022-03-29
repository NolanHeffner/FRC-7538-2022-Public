// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.subsystems.WheelSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WheelOperation extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * NOTE: Unused because of lack of testing, probably useful for flexible control of shooter system
   * Semantically the same as other commands
   */

  // Wheel control
  private WheelSystem m_subsystem;
  private double m_intakeSpeed, m_shooterSpeed;
  private double m_terminalIntakeSpeed;
  private double m_terminalShooterSpeed;

  // Timing system
  long m_startTime;
  long m_currentTime; 
  long m_waitTime;
  boolean isFinished = false;

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed, int milliseconds, double terminalIntakeSpeed, double terminalShooterSpeed) {
    m_subsystem = subsystem;
    m_intakeSpeed = intakeSpeed;
    m_shooterSpeed = shooterSpeed;
    m_terminalIntakeSpeed = terminalIntakeSpeed;
    m_terminalShooterSpeed = terminalShooterSpeed;
    m_waitTime = (long) milliseconds;
    addRequirements(subsystem);
  }

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed, int milliseconds) {
    this(subsystem, intakeSpeed, shooterSpeed, milliseconds, intakeSpeed, shooterSpeed);
  }

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed) {
    this(subsystem, intakeSpeed, shooterSpeed, 0, intakeSpeed, shooterSpeed);
  }

  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    m_subsystem.setIntakeWheelSpeed(m_intakeSpeed);
    m_subsystem.setShooterWheelSpeed(m_shooterSpeed);
    m_currentTime = System.currentTimeMillis();
    long m_differential = m_currentTime - m_startTime;
    isFinished = m_differential >= m_waitTime;
  }

  @Override
  public void end(boolean interrupted) {
    m_subsystem.setIntakeWheelSpeed(m_terminalIntakeSpeed);
    m_subsystem.setShooterWheelSpeed(m_terminalShooterSpeed);
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
