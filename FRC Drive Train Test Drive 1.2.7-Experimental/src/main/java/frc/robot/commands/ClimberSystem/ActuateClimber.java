// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberSystem;

import frc.robot.subsystems.ClimberSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ActuateClimber extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimberSystem m_subsystem;
  private final double speed;

  public ActuateClimber(ClimberSystem subsystem, double speed) {
    m_subsystem = subsystem;
    this.speed = speed;
    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    m_subsystem.set(speed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}