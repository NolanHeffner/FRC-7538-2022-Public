// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wait extends CommandBase {

  // Instantiates required subsystem and also timer variables that keep track of how long the subsystem has waited
  SubsystemBase m_subsystem;
  long m_startTime;
  long m_currentTime; 
  long m_waitTime;
  boolean isFinished = false;

  public Wait(SubsystemBase subsystem, int milliseconds) {
    m_waitTime = (long) milliseconds;
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
  }

  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    m_currentTime = System.currentTimeMillis();
    long m_differential = m_currentTime - m_startTime;
    isFinished = m_differential >= m_waitTime;
  }

  @Override
  public boolean isFinished() {
    return isFinished;
  }
}