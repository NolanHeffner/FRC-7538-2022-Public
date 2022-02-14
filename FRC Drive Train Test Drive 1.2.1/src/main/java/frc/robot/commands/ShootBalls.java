// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.WheelSystem;
import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class ShootBalls extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private WheelSystem m_subsystem;
  private BooleanSupplier XPressed;
  private BooleanSupplier YPressed;
  private BooleanSupplier BPressed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootBalls(WheelSystem subsystem, BooleanSupplier XPressed, BooleanSupplier YPressed, BooleanSupplier BPressed) {
    m_subsystem = subsystem;
    this.XPressed = XPressed;
    this.YPressed = YPressed;
    this.BPressed = BPressed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double shootSpeed = 0;
    if (BPressed.getAsBoolean()) {
      shootSpeed = Constants.HIGH_SHOOT_SPEED;
    } else if (YPressed.getAsBoolean()) {
      shootSpeed = Constants.MEDIUM_SHOOT_SPEED;
    } else if (XPressed.getAsBoolean()) {
      shootSpeed = Constants.LOW_SHOOT_SPEED;
    }
    m_subsystem.setShooterWheelSpeed(shootSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setShooterWheelSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
