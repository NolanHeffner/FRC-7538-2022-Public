// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.Constants;
import frc.robot.subsystems.WheelSystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeBalls extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private WheelSystem m_subsystem;
  private DoubleSupplier speed;

  public IntakeBalls(WheelSystem subsystem, DoubleSupplier leftTriggerDepression) {
    m_subsystem = subsystem;
    this.speed = leftTriggerDepression;
    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    double intakeSpeed = speed.getAsDouble() > Constants.LT_DEADBAND ? speed.getAsDouble() * Constants.MAX_INTAKE_SPEED : 0;
    m_subsystem.setIntakeWheelSpeed(intakeSpeed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
