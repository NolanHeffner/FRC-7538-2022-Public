// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Wait extends CommandBase {

  SubsystemBase m_subsystem;
  long m_startTime;
  long m_currentTime;
  long m_waitTime;
  int counter  = 0;

  /**
   * Creates a new WaitCommand.
   */
  public Wait(SubsystemBase subsystem, int milliseconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_waitTime = (long) milliseconds;
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_currentTime = System.currentTimeMillis();
    m_startTime = m_currentTime;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_currentTime = System.currentTimeMillis();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_currentTime - m_startTime) >= m_waitTime;
  }
}