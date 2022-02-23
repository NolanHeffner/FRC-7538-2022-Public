// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  WPI_TalonFX leftA = new WPI_TalonFX(Constants.LEFT_1_PORT);
  WPI_TalonFX leftB = new WPI_TalonFX(Constants.LEFT_2_PORT);
  WPI_TalonFX rightA = new WPI_TalonFX(Constants.RIGHT_1_PORT);
  WPI_TalonFX rightB = new WPI_TalonFX(Constants.RIGHT_2_PORT);
  SlewRateLimiter leftInputLimiter = new SlewRateLimiter(Constants.MAX_DRIVE_SPEED / Constants.TIME_TO_MAX_SPEED);
  SlewRateLimiter rightInputLimiter = new SlewRateLimiter(Constants.MAX_DRIVE_SPEED / Constants.TIME_TO_MAX_SPEED);

  public DriveTrain() {
    invertRightMotors(true);
  }

  public void invertRightMotors(boolean isInverted) {
    rightA.setInverted(isInverted);
    rightB.setInverted(isInverted);
  }

  // Yes, I know I can implement Differential Drive and just use arcadeDrive(frw, rot), I'm just bored plz don't judge
  public void arcadeDrive(double leftStickY, double rightStickX) {
        // Arcade-style driving; left stick forward/backward for driving speed, right stick left/right for turning
        double leftStickY_DB = deadBand(leftStickY, Constants.LY_DEADBAND);
        double rightStickX_DB = deadBand(rightStickX, Constants.RX_DEADBAND) * Constants.TURN_FACTOR;
        // No circular limitations because LY and RX are on different sticks, but scaling factor to prevent values outside of [-1,1]
        double leftInput = Constants.SCALING_FACTOR * (leftStickY_DB - rightStickX_DB);
        double rightInput = Constants.SCALING_FACTOR * (leftStickY_DB + rightStickX_DB);
        SmartDashboard.putNumber("Left Motor Speeds: ", leftInput);
        SmartDashboard.putNumber("Right Motor Speeds: ", rightInput);
        setMotors(leftInputLimiter.calculate(leftInput), rightInputLimiter.calculate(rightInput));
  }

  public double deadBand(double speed, double deadBand) {
    // Sets speeds to 0 if within deadband
    if(speed == 0 || Math.abs(speed) < deadBand) {
      return 0;
    }
    // Dilate to use whole range of speeds
    return speed * (1 - deadBand / Math.abs(speed)) / (1 - deadBand);
  }

  public void setMotors(double lSpeed, double rSpeed) {
    leftA.set(lSpeed);
    leftB.set(lSpeed);
    rightA.set(rSpeed);
    rightB.set(rSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
