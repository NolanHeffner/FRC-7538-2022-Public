// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DefaltDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_subsystem;
  private DoubleSupplier getLeftStickValue;
  private DoubleSupplier getRightStickValue;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DefaltDrive(DriveTrain subsystem, DoubleSupplier getleftStickValue, DoubleSupplier getRightStickValue) {
    m_subsystem = subsystem;
    this.getLeftStickValue = getLeftStickValue;
    this.getRightStickValue = getLeftStickValue;
  
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftStick = getLeftStickValue.getAsDouble();
    double rightStick = getRightStickValue.getAsDouble();
    double leftInput = (leftStick + rightStick)/2;
    double rightInput = (leftStick - rightStick)/2;
    m_subsystem.assignMotorSpeeds(leftInput, rightInput);
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
