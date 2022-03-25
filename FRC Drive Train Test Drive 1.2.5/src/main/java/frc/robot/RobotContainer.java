// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// All Drive Train commands and subsystems
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.Auto.Autonomous;
import frc.robot.commands.DriveTrain.WestCoastDrive;
// All Wheel System commands and subsystems
import frc.robot.subsystems.WheelSystem;
import frc.robot.commands.WheelSystem.IntakeBalls;
// import frc.robot.commands.WheelSystem.RunShooter; // Won't be uncommented unless we recalibrate T5
import frc.robot.commands.WheelSystem.RunIntake;
import frc.robot.commands.WheelSystem.ShootBalls;
import frc.robot.commands.Auto.AutoShoot;
import frc.robot.commands.WheelSystem.Jiggle;

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
  private final Autonomous autoCommand;

  // Instantiate driver controller
  public static XboxController driver = new XboxController(Constants.DRIVER_XBOX_PORT);

  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {
    m_driveTrain.setDefaultCommand(new WestCoastDrive(
      m_driveTrain,
      driver::getLeftY,
      driver::getRightX));
    m_wheelSystem.setDefaultCommand(
      new SequentialCommandGroup(
        new IntakeBalls(m_wheelSystem, driver::getLeftTriggerAxis),
        new ShootBalls(m_wheelSystem, driver::getRightTriggerAxis)));
    autoCommand = new Autonomous(m_driveTrain, m_wheelSystem);
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
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}