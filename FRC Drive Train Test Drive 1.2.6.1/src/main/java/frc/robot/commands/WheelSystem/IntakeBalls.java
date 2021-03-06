// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.Constants;
import frc.robot.subsystems.WheelSystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakeBalls extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  // Creates private variables that we can use dependency injection to assign values to; will be used to send instructions to wheel subsystem
  private WheelSystem m_subsystem;
  private DoubleSupplier speed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeBalls(WheelSystem subsystem, DoubleSupplier leftTriggerDepression) {
    // Dependency injection of constructor parameters into local variables
    m_subsystem = subsystem;
    this.speed = leftTriggerDepression;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // D:<
    // If the speed (directly mapped to leftTriggerDepression) is outside of the deadband, it returns the proportional intake speed
    double intakeSpeed = speed.getAsDouble() > Constants.LT_DEADBAND ? speed.getAsDouble() * Constants.MAX_INTAKE_SPEED : 0;
    // Sets scaled intake speed to intake wheel motors
    m_subsystem.setIntakeWheelSpeed(intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_subsystem.setIntakeWheelSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
