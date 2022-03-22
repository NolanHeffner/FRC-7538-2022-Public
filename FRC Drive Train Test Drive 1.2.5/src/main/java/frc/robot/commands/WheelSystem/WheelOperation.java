// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WheelSystem;

//import frc.robot.commands.Auto.CommandTimer;
//import frc.robot.subsystems.WheelSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class WheelOperation extends CommandBase {
  /*@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  // Creates private variables that we can use dependency injection to assign values to; will be used to send instructions to wheel subsystem
  private WheelSystem m_subsystem;
  private double intakeSpeed, shooterSpeed;
  private CommandTimer timer = new CommandTimer();
  private double terminalIntakeSpeed;
  private double terminalShooterSpeed;

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed, int milliseconds, double terminalIntakeSpeed, double terminalShooterSpeed) {
    // Dependency injection of constructor parameters into local variables
    m_subsystem = subsystem;
    this.intakeSpeed = intakeSpeed;
    this.shooterSpeed = shooterSpeed;
    this.terminalIntakeSpeed = terminalIntakeSpeed;
    this.terminalShooterSpeed = terminalShooterSpeed;
    timer.resetTimer(milliseconds);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed, int milliseconds) {
    this(subsystem, intakeSpeed, shooterSpeed, milliseconds, intakeSpeed, shooterSpeed);
  }

  public WheelOperation(WheelSystem subsystem, double intakeSpeed, double shooterSpeed) {
    this(subsystem, intakeSpeed, shooterSpeed, 0, intakeSpeed, shooterSpeed);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.setIntakeWheelSpeed(intakeSpeed);
    m_subsystem.setShooterWheelSpeed(shooterSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setIntakeWheelSpeed(terminalIntakeSpeed);
    m_subsystem.setShooterWheelSpeed(terminalShooterSpeed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.isFinished();
  }*/
}
