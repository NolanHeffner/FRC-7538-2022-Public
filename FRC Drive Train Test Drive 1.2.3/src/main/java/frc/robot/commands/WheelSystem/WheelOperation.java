// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.WheelSystem;

/** A complex auto command that drives forward, releases a hatch, and then drives backward. */
public class WheelOperation extends ParallelCommandGroup {
  /**
   * Creates a new WheelOperation
   * @param subsysten The drive subsystem this command will run on
   * @param XPressed,YPressed,BPressed The button presses registered by the ShootBalls command
   * @param rightTrigger Returns the amount of pull on the rightTrigger to determine speed of intake wheel
   */
  public WheelOperation(WheelSystem subsystem, DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
    addCommands(
        // Run intake depending on how far the right trigger is pulled
        new RunIntake(subsystem, leftTrigger),
        // Shoot the balls depending on XYB-presses
        new ShootBalls(subsystem, rightTrigger));
  }
}