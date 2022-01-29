// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class WestCoastDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private DriveTrain m_subsystem;
  private double leftStickY, rightStickX;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WestCoastDrive(DriveTrain subsystem, DoubleSupplier leftStickY, DoubleSupplier rightStickX) {
    m_subsystem = subsystem;
    this.leftStickY = leftStickY.getAsDouble();
    this.rightStickX = rightStickX.getAsDouble();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Arcade-style driving; left stick forward/backward for driving speed, right stick left/right for turning
    //double leftStickY = RobotContainer.driver.getLeftY();
    //double rightStickX = RobotContainer.driver.getRightX();

    // Differential mathematics; yes Anthony, I used your suggestion :) let's hope it's good; if not, we can always redo it
    double leftInput = 0.5 * (leftStickY + rightStickX);
    double rightInput = 0.5 * (leftStickY - rightStickX);
    double scalingFactor = 1 / Math.min(leftInput,rightInput);
    leftInput *= scalingFactor;
    rightInput *= scalingFactor;

    m_subsystem.setLeftMotors(leftInput);
    m_subsystem.setRightMotors(rightInput);
    // Need to figure out axes limitations on XBox controller
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setLeftMotors(0);
    m_subsystem.setRightMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
