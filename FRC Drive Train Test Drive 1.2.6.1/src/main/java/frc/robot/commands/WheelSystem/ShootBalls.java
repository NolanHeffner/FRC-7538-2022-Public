// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import frc.robot.subsystems.WheelSystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class ShootBalls extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  // Creates private variables that we can use dependency injection to assign values to; will be used to send instructions to wheel subsystem
  private WheelSystem m_subsystem;
  private DoubleSupplier rightTriggerDepression;

  public ShootBalls(WheelSystem subsystem, DoubleSupplier rightTriggerDepression) {
    // Dependency injection of constructor parameters into local variables
    m_subsystem = subsystem;
    this.rightTriggerDepression = rightTriggerDepression;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    // If the rightTrigger value depressed beyond the deadband, it returns that the ball shooter should run
    // Sets speed of shooter wheel motor to correct speed
    // double shootSpeed = rightTriggerDepressed ? SmartDashboard.getNumber("Shooter Wheel Speed", Constants.DEFAULT_SHOOT_SPEED) : 0;
    double shootSpeed = Math.abs(rightTriggerDepression.getAsDouble()) > Constants.RT_DEADBAND ? Constants.DEFAULT_SHOOT_SPEED : 0;
    m_subsystem.setShooterWheelSpeed(shootSpeed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
