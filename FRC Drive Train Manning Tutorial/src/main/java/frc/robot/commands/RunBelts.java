// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ConveyerBelts;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class RunBelts extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private ConveyerBelts m_subsystem;
  private boolean XPressed, YPressed, BPressed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RunBelts(ConveyerBelts subsystem, BooleanSupplier XPressed, BooleanSupplier YPressed, BooleanSupplier BPressed) {
    m_subsystem = subsystem;
    this.XPressed = XPressed.getAsBoolean();
    this.YPressed = YPressed.getAsBoolean();
    this.BPressed = BPressed.getAsBoolean();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double beltSpeed = 0;
    if(BPressed) {
      beltSpeed = Constants.HIGH_BELT_SPEED;
    } else if(YPressed) {
      beltSpeed = Constants.MEDIUM_BELT_SPEED;
    } else if(XPressed) {
      beltSpeed = Constants.LOW_BELT_SPEED;
    }
    m_subsystem.setBeltSpeed(beltSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setBeltSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
