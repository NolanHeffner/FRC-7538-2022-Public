// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.subsystems.WheelSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooter extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private WheelSystem m_subsystem;
  private double speed;

  public RunShooter(WheelSystem subsystem, double speed) {
    m_subsystem = subsystem;
    this.speed = speed;
    addRequirements(subsystem);
  }

@Override
  public void execute() {
    m_subsystem.setShooterWheelSpeed(speed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
