// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.WheelSystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class BallOutput extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final WheelSystem m_subsystem;
  private DoubleSupplier getRightTriggerValue;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public BallOutput(WheelSystem subsystem, DoubleSupplier getRightTriggerValue) {
    m_subsystem = subsystem;
    this.getRightTriggerValue = getRightTriggerValue;
    

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rightTrigger = getRightTriggerValue.getAsDouble();
    double HighInput = (rightTrigger);
    m_subsystem.assignOutputSpeed(HighInput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
