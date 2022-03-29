// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// DEPRECATED - TOO SCARY BC COULD BREAK ARM
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

/*
package frc.robot.commands.ClimberSystem;

import frc.robot.Constants;
import frc.robot.subsystems.ClimberSystem;
import frc.robot.subsystems.ClimberSystem.Mode;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class AutoRetractActuator extends PIDCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ClimberSystem m_subsystem;
  private boolean emergencyStop = false;

  public AutoRetractActuator(ClimberSystem subsystem) {
    super(
      new PIDController(Constants.kClimbP, Constants.kClimbI, Constants.kClimbD),
      // Close loop on heading
      subsystem::getEncoderAverage,
      // Set reference to target
      -Constants.CLIMB_DISTANCE,
      // Pipe output to turn robot
      output -> subsystem.set(
        Math.min(output, Constants.MAX_CLIMB_SPEED)),
      // Require the drive
      subsystem);
    getController()
      .setTolerance(Constants.kClimbPTol, Constants.kClimbDTol);

    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(m_subsystem.getIsExtended() == false) {
      m_subsystem.setMode(Mode.BRAKE);
      m_subsystem.resetEncoders();
      // m_drivetrain.resetHeading();
      super.initialize();
    } else {
      emergencyStop = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.isExtended(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (getController().atSetpoint() || emergencyStop);
  }
}
*/