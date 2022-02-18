// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  WPI_TalonFX leftA = new WPI_TalonFX(Constants.LEFT_1_PORT);
  WPI_TalonFX leftB = new WPI_TalonFX(Constants.LEFT_2_PORT);
  WPI_TalonFX rightA = new WPI_TalonFX(Constants.RIGHT_1_PORT);
  WPI_TalonFX rightB = new WPI_TalonFX(Constants.RIGHT_2_PORT);
  SlewRateLimiter leftInputLimiter = new SlewRateLimiter(4.5 * Constants.MAX_DRIVE_SPEED);
  SlewRateLimiter rightInputLimiter = new SlewRateLimiter(4.5 * Constants.MAX_DRIVE_SPEED);

  public DriveTrain() {
    invertRightMotors();
  }

  public void invertRightMotors() {
    rightA.setInverted(true);
    rightB.setInverted(true);
  }

  // Yes, I know I can implement Differential Drive and just use arcadeDrive(frw, rot), I'm just bored plz don't judge
  public void arcadeDrive(double leftStickY, double rightStickX) {
        // Arcade-style driving; left stick forward/backward for driving speed, right stick left/right for turning
        double leftStickY_DB = deadBand(leftStickY, Constants.LY_DEADBAND);
        double rightStickX_DB = deadBand(rightStickX, Constants.RX_DEADBAND) * Constants.TURN_FACTOR;
        // No circular limitations because LY and RX are on different sticks, but scaling factor to prevent values outside of [-1,1]
        double leftInput = Constants.SCALING_FACTOR * (leftStickY_DB - rightStickX_DB);
        double rightInput = Constants.SCALING_FACTOR * (leftStickY_DB + rightStickX_DB);
        setMotors(leftInputLimiter.calculate(leftInput), rightInputLimiter.calculate(rightInput));
  }

  public double deadBand(double speed, double deadBand) {
    // Sets speeds to 0 if within deadband
    double newSpeed = Math.abs(speed) > deadBand ? speed : 0;
    // If speed is 0, always return 0; prevents div by 0 error
    if(speed == 0) {
      return 0;
    }
    // Dilate to use whole range of speeds
    double sign = newSpeed / Math.abs(newSpeed);
    return (speed - sign * deadBand) / (1 - deadBand);
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
