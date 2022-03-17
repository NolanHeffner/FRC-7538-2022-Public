// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.commands.Auto.CommandTimer;
import frc.robot.subsystems.WheelSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WheelOperation extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  // Creates private variables that we can use dependency injection to assign values to; will be used to send instructions to wheel subsystem
  private WheelSystem m_subsystem;
  private double m_intakeSpeed, m_shooterSpeed;
  private CommandTimer m_timer = new CommandTimer();
  private double m_terminalIntakeSpeed;
  private double m_terminalShooterSpeed;
  private int m_milliseconds;

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed, int milliseconds, double terminalIntakeSpeed, double terminalShooterSpeed) {
    // Dependency injection of constructor parameters into local variables
    m_subsystem = subsystem;
    m_intakeSpeed = intakeSpeed;
    m_shooterSpeed = shooterSpeed;
    m_terminalIntakeSpeed = terminalIntakeSpeed;
    m_terminalShooterSpeed = terminalShooterSpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed, int milliseconds) {
    this(subsystem, intakeSpeed, shooterSpeed, milliseconds, intakeSpeed, shooterSpeed);
    // Sets terminal speed to mid-command speed so speed persists after command ends
  }

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed) {
    this(subsystem, intakeSpeed, shooterSpeed, 0, intakeSpeed, shooterSpeed);
    // Sets time to 0; aka sets to shooter speed and immediately returns as finished
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.resetTimer(m_milliseconds);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.setIntakeWheelSpeed(m_intakeSpeed);
    m_subsystem.setShooterWheelSpeed(m_shooterSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setIntakeWheelSpeed(m_terminalIntakeSpeed);
    m_subsystem.setShooterWheelSpeed(m_terminalShooterSpeed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.isFinished(); // Likely does not work?
  }
}
