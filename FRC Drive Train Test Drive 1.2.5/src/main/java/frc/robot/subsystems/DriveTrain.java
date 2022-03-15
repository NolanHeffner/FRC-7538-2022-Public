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
  WPI_TalonFX leftA = new WPI_TalonFX(Constants.LEFT_1_CAN_ID);
  WPI_TalonFX leftB = new WPI_TalonFX(Constants.LEFT_2_CAN_ID);
  WPI_TalonFX rightA = new WPI_TalonFX(Constants.RIGHT_1_CAN_ID);
  WPI_TalonFX rightB = new WPI_TalonFX(Constants.RIGHT_2_CAN_ID);

  // Create chooser for accel limiting
  SendableChooser<Boolean> m_limit_chooser = new SendableChooser<>();
  boolean m_currentChoice = false;

  // Creates slew rate limiters in case we decide to limit acceleration for motor protection
  AdjustableSlewRateLimiter leftInputLimiter = new AdjustableSlewRateLimiter(Constants.DRIVE_TRAIN_RATE_LIMIT);
  AdjustableSlewRateLimiter rightInputLimiter = new AdjustableSlewRateLimiter(Constants.DRIVE_TRAIN_RATE_LIMIT);

  /*
  *   Code that initializes the drive train
  */

  public DriveTrain() {
    // Inverts right motors so that positive inputs move robot forward
    invertRightMotors(true);
    setMode(Mode.COAST);

    leftB.follow(leftA);
    rightB.follow(rightA);

    m_limit_chooser.setDefaultOption("No limit", false);
    m_limit_chooser.addOption("Limited", true);
  }

  public void invertRightMotors(boolean isInverted) {
    rightA.setInverted(isInverted);
    rightB.setInverted(isInverted);
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
        break;
      case BRAKE:
        leftA.setNeutralMode(NeutralMode.Brake);
        leftB.setNeutralMode(NeutralMode.Brake);
        rightA.setNeutralMode(NeutralMode.Brake);
        rightB.setNeutralMode(NeutralMode.Brake);
        break;
    }
  }

  /*
  *   Code that handles the driving
  */

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
        // Update acceleration limit to option selected on SmartDashboard
        // leftInputLimiter.setRateLimit(SmartDashboard.getNumber("", defaultValue));
        if (m_limit_chooser.getSelected() != m_currentChoice) {
          m_currentChoice = m_limit_chooser.getSelected();
          leftInputLimiter.reset(leftInput);
          rightInputLimiter.reset(rightInput);
        }
        // Sets motor speeds
        if (m_limit_chooser.getSelected()) {
          setMotors(leftInputLimiter.calculate(leftInput), rightInputLimiter.calculate(rightInput));
        } else {
          setMotors(leftInput, rightInput);
        }
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
    rightA.set(rSpeed);
  }

  /*
  *   Handles encodes for PID and also misc. utilities
  */

  public void resetEncoders() {
    leftA.setSelectedSensorPosition(0);
    rightA.setSelectedSensorPosition(0);
  }

  public double getEncoderPosition() { // Not sure how works exactly want to redo
    return 0.5 * leftA.getSelectedSensorPosition() + 0.5 * rightA.getSelectedSensorPosition();
    // Note: 2048 units per rotation; use gear ratio to find distance travelled per raw sensor unit
  }

  public void displayTemperatures() {
    SmartDashboard.putNumber("Talon ID1 Temperature: ", leftA.getTemperature());
    SmartDashboard.putNumber("Talon ID2 Temperature: ", leftB.getTemperature());
    SmartDashboard.putNumber("Talon ID3 Temperature: ", rightA.getTemperature());
    SmartDashboard.putNumber("Talon ID4 Temperature: ", rightB.getTemperature());
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
