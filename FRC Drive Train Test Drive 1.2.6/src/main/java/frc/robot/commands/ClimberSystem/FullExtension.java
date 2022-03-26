// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberSystem;

import frc.robot.commands.Auto.Wait;
import frc.robot.subsystems.ClimberSystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class FullExtension extends SequentialCommandGroup {
  /**
   * Schedules all the necessary commands to automatically shoot a ball
   * First, gets shooter system up to speed (since motors take a little bit to get to full speed)
   * Then, uses the intake wheels (where balls are stored since we have no official storage) to feed the balls into the shooter
   * Finally, turns both off
   */
  public FullExtension(ClimberSystem climberSystem) {
    addCommands(
      new ExtendActuator(climberSystem),
      new Wait(climberSystem, 750));
  }
}