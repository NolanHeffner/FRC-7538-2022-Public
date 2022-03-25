// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import frc.robot.subsystems.DriveTrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WestCoastDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  // Creates private variables that we can use dependency injection to assign values to; will be used to send instructions to drive subsystem
  private DriveTrain m_subsystem;
  private DoubleSupplier leftStickY, rightStickX;
  // private BooleanSupplier xPressed;

  public WestCoastDrive(DriveTrain subsystem, DoubleSupplier leftStickY, DoubleSupplier rightStickX, BooleanSupplier xPressed) {
    // Dependency injection of constructor parameters into local variables
    m_subsystem = subsystem;
    this.leftStickY = leftStickY;
    this.rightStickX = rightStickX;
    // this.xPressed = xPressed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Calls the custom arcadeDrive method which converts stick axes values to motor inputs in DriveTrain.java
    double leftStickYAsDouble = leftStickY.getAsDouble();
    double rightStickXAsDouble = rightStickX.getAsDouble();
    
    /* Uncommented when receive limelight
    double Kp = -0.1;
    double min_command = 0.05;
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

    if (xPressed.getAsBoolean()) {
      double heading_error = -tx;
      if (tx > 1.0) {
        rightStickXAsDouble += Kp * heading_error - min_command;
      } else if (tx < 1.0) {
        rightStickXAsDouble += Kp * heading_error + min_command;
      }
      MathUtil.clamp(rightStickXAsDouble, -1, 1);
    }*/

    m_subsystem.arcadeDrive(leftStickYAsDouble, rightStickXAsDouble);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Emergency stop of motors if something goes wrong in the code
    m_subsystem.setMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
