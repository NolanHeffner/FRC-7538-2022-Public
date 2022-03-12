// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Auto.Wait;
import frc.robot.subsystems.WheelSystem;


public class Jiggle extends SequentialCommandGroup {
  /**
   * Schedules all the necessary commands to automatically shoot a ball
   * First, gets shooter system up to speed (since motors take a little bit to get to full speed)
   * Then, uses the intake wheels (where balls are stored since we have no official storage) to feed the balls into the shooter
   * Finally, turns both off
   */
  public Jiggle(WheelSystem wheelSystem) {
    addCommands(
      new RunIntake(wheelSystem, () -> -0.3),
      // new RunShooter(wheelSystem, () -> -0.3),
      new Wait(wheelSystem, 150),
      new RunIntake(wheelSystem, () -> 0.2),
      // new RunShooter(wheelSystem, () -> 0.2),
      new Wait(wheelSystem, 200),
      // new RunIntake(wheelSystem, () -> -0.1),
      // new RunShooter(wheelSystem, () -> -0.1),
      // new Wait(wheelSystem, 200),
      new RunIntake(wheelSystem, () -> 0));
      // new RunShooter(wheelSystem, () -> 0));
  }
}