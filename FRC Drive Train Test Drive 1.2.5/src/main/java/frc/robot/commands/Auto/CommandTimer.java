// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

public class CommandTimer {

  // Instantiates required subsystem and also timer variables that keep track of how long the subsystem has waited
  static long m_startTime;
  static long m_waitTime;
  long m_currentTime;
  boolean isFinished = false;

  /**
   * Creates a new WaitCommand.
   */
  public CommandTimer(int milliseconds) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_startTime = System.currentTimeMillis();
    m_waitTime = (long) milliseconds;
  }

  public void resetTimer(int milliseconds) {
    m_startTime = System.currentTimeMillis();
    m_waitTime =  milliseconds;
  }

  public boolean isFinished() {
    // Updates current time every execution of the command
    m_currentTime = System.currentTimeMillis();
    long m_differential = m_currentTime - m_startTime;
    return m_differential >= m_waitTime;
  }
}