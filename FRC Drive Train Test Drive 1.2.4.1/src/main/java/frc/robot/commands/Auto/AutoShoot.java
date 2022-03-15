// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.WheelSystem.WheelOperation;
import frc.robot.subsystems.WheelSystem;


public class AutoShoot extends SequentialCommandGroup {
  /**
   * Schedules all the necessary commands to automatically shoot a ball
   * First, gets shooter system up to speed (since motors take a little bit to get to full speed)
   * Then, uses the intake wheels (where balls are stored since we have no official storage) to feed the balls into the shooter
   * Finally, turns both off
   */
  public AutoShoot(WheelSystem wheelSystem) {
    addCommands(
      new WheelOperation(wheelSystem, 0, Constants.DEFAULT_SHOOT_SPEED, 1000),
      new WheelOperation(wheelSystem, Constants.FEED_SPEED, Constants.DEFAULT_SHOOT_SPEED, 2500, 0, 0));
  }
}