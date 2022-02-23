// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.WheelSystem.RunIntake;
import frc.robot.commands.WheelSystem.ShootBalls;
import frc.robot.subsystems.WheelSystem;

public class AutoShoot extends SequentialCommandGroup {
  public AutoShoot(WheelSystem wheelSystem) {
    addCommands(
      new ShootBalls(wheelSystem, () -> 1),
      new Wait(wheelSystem, 200),
      new RunIntake(wheelSystem, () -> Constants.FEED_SPEED),
      new Wait(wheelSystem, 1000),
      new RunIntake(wheelSystem, () -> 0),
      new ShootBalls(wheelSystem, () -> 0));
  }
}