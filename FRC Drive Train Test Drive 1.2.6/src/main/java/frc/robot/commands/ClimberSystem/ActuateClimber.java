// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberSystem;

import frc.robot.Constants;
import frc.robot.subsystems.ClimberSystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ActuateClimber extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimberSystem m_subsystem;
  private final DoubleSupplier speed;

  public ActuateClimber(ClimberSystem subsystem, DoubleSupplier speed) {
    m_subsystem = subsystem;
    this.speed = speed;
    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    m_subsystem.set(MathUtil.clamp(speed.getAsDouble(), -Constants.MAX_CLIMB_SPEED, Constants.MAX_CLIMB_SPEED));
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}