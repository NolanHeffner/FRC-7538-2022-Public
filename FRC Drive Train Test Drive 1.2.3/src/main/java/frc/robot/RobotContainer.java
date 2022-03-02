// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.Auto.Wait;
import frc.robot.commands.WestCoastDrive;
import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.WheelSystem;
// import frc.robot.commands.WheelSystem.WheelOperation;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Create subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Wait autoDrive;
  // private final WheelSystem m_wheelSystem = new WheelSystem();

  // Instantiate driver controller
  public static XboxController driver = new XboxController(Constants.DRIVER_XBOX_PORT);

  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {
    // We use method reference with object::method so command scheduler can constantly update controller values to commands in background
    m_driveTrain.setDefaultCommand(new WestCoastDrive(
      m_driveTrain,
      driver::getLeftY,
      driver::getRightX));
    // Wheel subsystem is currently not built in robot so we keep the robot from thinking there are motors that don't exist
    /*m_wheelSystem.setDefaultCommand(new WheelOperation(
      m_wheelSystem,
      driver::getLeftTriggerAxis,
      driver::getRightTriggerAxis));*/
    // Autonomous mode is currently the robot going 'huh, guess i'll wait a bajillion seconds'
    autoDrive = new Wait(m_driveTrain, 10000);
    // Configure the button bindings
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
    // JoystickButton xboxControllerAButton = new JoystickButton(driver, Constants.XBOX_A_BUTTON);

    // Link buttons to commands
    // xboxControllerAButton.whenPressed(new RunIntake(m_wheelSystem, () -> Constants.INTAKE_SPEED)).whenReleased(new RunIntake(m_wheelSystem, () -> 0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoDrive;
  }
}