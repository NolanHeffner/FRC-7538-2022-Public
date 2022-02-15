// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class WestCoastDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private DriveTrain m_subsystem;
  private DoubleSupplier leftStickY, rightStickX;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WestCoastDrive(DriveTrain subsystem, DoubleSupplier leftStickY, DoubleSupplier rightStickX) {
    m_subsystem = subsystem;
    this.leftStickY = leftStickY;
    this.rightStickX = rightStickX;
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
    double leftStickYAsDouble = deadBand(leftStickY.getAsDouble(), Constants.LY_DEADBAND);
    double rightStickXAsDouble = deadBand(rightStickX.getAsDouble(), Constants.RX_DEADBAND) * Constants.TURN_FACTOR;
    // No circular limitations because LY and RX are on different sticks, but scaling factor to prevent values outside of [-1,1]
    double leftInput = Constants.SCALING_FACTOR * (leftStickYAsDouble + rightStickXAsDouble);
    double rightInput = Constants.SCALING_FACTOR * (leftStickYAsDouble - rightStickXAsDouble);
    m_subsystem.setMotors(leftInput, rightInput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setMotors(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public double deadBand(double speed, double deadBand) {
    // Sets speeds to 0 if within deadband
    double newSpeed = Math.abs(speed) > deadBand ? speed : 0;
    // If speed is 0, always return 0; prevents div by 0 error
    if(speed == 0) {
      return 0;
    }
    // Dilate to use whole range of speeds
    double sign = newSpeed / Math.abs(newSpeed);
    return (speed - sign * deadBand) / (1 - deadBand);
  }
}
