// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveTrain.setMotorMode;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.WheelSystem;
import frc.robot.subsystems.DriveTrain.Mode;


public class Autonomous extends SequentialCommandGroup {
  /**
   * Schedules all the necessary commands to automatically drive back 3 feet and shoot a ball
   * First, gets drive train to drive backwards for a practically determined amount of time;
   * Then, gets the shooter system up to speed (since motors take a little bit to get to full speed)
   * Next, uses the intake wheels (where balls are stored since we have no official storage) to feed the balls into the shooter
   * Finally, turns all off
   */
  public Autonomous(DriveTrain driveTrain, WheelSystem wheelSystem) {
    addCommands(
      new setMotorMode(driveTrain, Mode.BRAKE),
      new AutoDrive(driveTrain, 0.305 * -8.6), // Drive 9.5 feet back
      new AutoShoot(wheelSystem),
      new Wait(driveTrain, 1000),
      new setMotorMode(driveTrain, Mode.COAST));
  }
}