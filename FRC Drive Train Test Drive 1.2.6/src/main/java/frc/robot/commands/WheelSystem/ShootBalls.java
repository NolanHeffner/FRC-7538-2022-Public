// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.subsystems.WheelSystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class ShootBalls extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private WheelSystem m_subsystem;
  private DoubleSupplier rightTriggerDepression;

  public ShootBalls(WheelSystem subsystem, DoubleSupplier rightTriggerDepression) {
    m_subsystem = subsystem;
    this.rightTriggerDepression = rightTriggerDepression;
    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    double shootSpeed = Math.abs(rightTriggerDepression.getAsDouble()) > Constants.RT_DEADBAND ? Constants.DEFAULT_SHOOT_SPEED : 0;
    m_subsystem.setShooterWheelSpeed(shootSpeed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
