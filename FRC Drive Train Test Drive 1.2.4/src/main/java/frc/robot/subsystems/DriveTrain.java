// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;
import frc.robot.filters.AdjustableSlewRateLimiter;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  // Instantiates motors that subsystem controls to link code to CAN bus
  WPI_TalonFX leftA = new WPI_TalonFX(Constants.LEFT_1_PORT);
  WPI_TalonFX leftB = new WPI_TalonFX(Constants.LEFT_2_PORT);
  WPI_TalonFX rightA = new WPI_TalonFX(Constants.RIGHT_1_PORT);
  WPI_TalonFX rightB = new WPI_TalonFX(Constants.RIGHT_2_PORT);

  // Create chooser for accel limiting
  SendableChooser<Double> m_limit_chooser = new SendableChooser<>();
  double accelLimit = 0;

  // Creates slew rate limiters in case we decide to limit acceleration for motor protection
  AdjustableSlewRateLimiter leftInputLimiter = new AdjustableSlewRateLimiter(accelLimit);
  AdjustableSlewRateLimiter rightInputLimiter = new AdjustableSlewRateLimiter(accelLimit);

  public DriveTrain() {
    // Inverts right motors so that positive inputs move robot forward
    invertRightMotors(true);

    m_limit_chooser.setDefaultOption("No limit", (double) 0);
    m_limit_chooser.addOption("Limited", (double) Constants.MAX_DRIVE_SPEED / Constants.TIME_TO_MAX_SPEED);
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
        // Display numbers to SmartDashboard to help inform drivers, especially during troubleshooting tests
        SmartDashboard.putNumber("Left Motor Speeds: ", leftInput);
        SmartDashboard.putNumber("Right Motor Speeds: ", rightInput);
        // Sets motor speeds
        //setMotors(leftInputLimiter.calculate(leftInput), rightInputLimiter.calculate(rightInput));
        // Update acceleration limit to option selected on SmartDashboard
        accelLimit = m_limit_chooser.getSelected();
        leftInputLimiter.setRateLimit(accelLimit);
        rightInputLimiter.setRateLimit(accelLimit);
        // leftInputLimiter.setRateLimit(SmartDashboard.getNumber("", defaultValue));
        setMotors(leftInputLimiter.calculate(leftInput), rightInputLimiter.calculate(rightInput));
      }

  public double deadBand(double speed, double deadBand) {
    // Sets speeds to 0 if within deadband
    if(speed == 0 || Math.abs(speed) < deadBand) {
      return 0;
    }
    // Dilate to use whole range of speeds; obfuscated a lil' bit
    return speed * (1 - deadBand / Math.abs(speed)) / (1 - deadBand);
  }

  public void setMotors(double lSpeed, double rSpeed) {
    // Pushes new speeds to drive train using set() method
    leftA.set(lSpeed);
    leftB.set(lSpeed);
    rightA.set(rSpeed);
    rightB.set(rSpeed);
  }

  public enum Mode {
    COAST,
    BRAKE
  }

  public void setMode(Mode mode) {
    switch(mode) {
      case COAST:
        leftA.setNeutralMode(NeutralMode.Coast);
        leftB.setNeutralMode(NeutralMode.Coast);
        rightA.setNeutralMode(NeutralMode.Coast);
        rightB.setNeutralMode(NeutralMode.Coast);
      case BRAKE:
        leftA.setNeutralMode(NeutralMode.Brake);
        leftB.setNeutralMode(NeutralMode.Brake);
        rightA.setNeutralMode(NeutralMode.Brake);
        rightB.setNeutralMode(NeutralMode.Brake);
    }
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
