// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrain.Mode;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class setMotorMode extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  // Creates private variables that we can use dependency injection to assign values to; will be used to send instructions to wheel subsystem
  private DriveTrain m_subsystem;
  private Mode m_mode;

  public setMotorMode(DriveTrain subsystem, Mode mode) {
    // Dependency injection of constructor parameters into local variables
    m_subsystem = subsystem;
    m_mode = mode;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.setMode(m_mode);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}