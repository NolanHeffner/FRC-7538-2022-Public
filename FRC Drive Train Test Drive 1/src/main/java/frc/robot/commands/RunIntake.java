// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.WheelSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class RunIntake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private WheelSystem m_subsystem;
  private boolean APressed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RunIntake(WheelSystem subsystem, boolean APressed) {
    m_subsystem = subsystem;
    this.APressed = APressed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double shootSpeed = APressed ? Constants.INTAKE_SPEED : 0;
    m_subsystem.setIntakeWheelSpeed(shootSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setShooterWheelSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
