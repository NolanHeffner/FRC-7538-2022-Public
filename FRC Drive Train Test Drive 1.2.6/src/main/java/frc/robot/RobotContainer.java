// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// All Drive Train commands and subsystems
import frc.robot.subsystems.DriveTrain;
//import frc.robot.commands.Auto.Autonomous;
import frc.robot.commands.DriveTrain.WestCoastDrive;

// All Wheel System commands and subsystems
import frc.robot.subsystems.WheelSystem;
import frc.robot.commands.WheelSystem.IntakeBalls;
import frc.robot.commands.WheelSystem.RunIntake;
import frc.robot.commands.WheelSystem.ShootBalls;
import frc.robot.commands.Auto.AutoShoot;
import frc.robot.commands.WheelSystem.Jiggle;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
// All Auto / Trajectory imports
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Create subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final WheelSystem m_wheelSystem = new WheelSystem();
  //private final Autonomous autoCommand;

  // Instantiate driver controller
  public static XboxController driver = new XboxController(Constants.DRIVER_XBOX_PORT);

  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {
    m_driveTrain.setDefaultCommand(new WestCoastDrive(
      m_driveTrain,
      driver::getLeftY,
      driver::getRightX,
      driver::getXButtonPressed));
    m_wheelSystem.setDefaultCommand(
      new SequentialCommandGroup(
        new IntakeBalls(m_wheelSystem, driver::getLeftTriggerAxis),
        new ShootBalls(m_wheelSystem, driver::getRightTriggerAxis)));
    //autoCommand = new Autonomous(m_driveTrain, m_wheelSystem);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create buttons to link to commands
    // JoystickButton joystickButton1 = new JoystickButton(driver1, Constants.JOYSTICK_AUXILIARY_BUTTON_1);
    JoystickButton xboxControllerAButton = new JoystickButton(driver, Constants.XBOX_A_BUTTON);
    JoystickButton xboxControllerBButton = new JoystickButton(driver, Constants.XBOX_B_BUTTON);
    // JoystickButton xboxControllerXButton = new JoystickButton(driver, Constants.XBOX_X_BUTTON);
    JoystickButton xboxControllerLeftBumper = new JoystickButton(driver, Constants.XBOX_LEFT_BUMPER);
    // JoystickButton xboxControllerRightBumper = new JoystickButton(driver, Constants.XBOX_RIGHT_BUMPER);

    // Link buttons to commands
    //xboxControllerAButton.whenPressed(new RunIntake(m_wheelSystem, () -> Constants.INTAKE_SPEED)).whenReleased(new RunIntake(m_wheelSystem, () -> 0));
    xboxControllerAButton.whenPressed(new AutoShoot(m_wheelSystem));
    xboxControllerBButton.whenPressed(new Jiggle(m_wheelSystem));
    xboxControllerLeftBumper.whileHeld(new RunIntake(m_wheelSystem, -0.3));
    // xboxControllerRightBumper.whileHeld(new RunShooter(m_wheelSystem, () -> -2)); // Does not work lol bc shooter is so jank
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    var autoVoltageConstraint =
    new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(
          Constants.kVoltsPos,
          Constants.kVoltsVel,
          Constants.kVoltsAcc),
          DriveTrain.kDriveKinematics,
          10);

    TrajectoryConfig config =
    new TrajectoryConfig(
            Constants.MAX_SPEED_IN_METERS_PER_SECOND,
            Constants.MAX_ACCEL_IN_METERS_PER_SECOND)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(DriveTrain.kDriveKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint);
          
    // An example trajectory to follow.  All units in meters.
    Trajectory exampleTrajectory =
    TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(3, 0, new Rotation2d(0)),
        // Pass config
        config);
    
    RamseteCommand ramseteCommand =
    new RamseteCommand(
        exampleTrajectory,
        m_driveTrain::getPose,
        new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
        new SimpleMotorFeedforward(
          Constants.kVoltsPos,
          Constants.kVoltsVel,
          Constants.kVoltsAcc),
          DriveTrain.kDriveKinematics,
          m_driveTrain::getWheelSpeeds,
          new PIDController(Constants.kPDriveVel, 0, 0),
          new PIDController(Constants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_driveTrain::tankDriveVolts,
        m_driveTrain);

    // A RamseteCommand will run in autonomous
    return ramseteCommand.andThen(() -> m_driveTrain.tankDriveVolts(0, 0));
  }
}
