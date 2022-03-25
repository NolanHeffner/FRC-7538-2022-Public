// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSystem extends SubsystemBase {

  // Instantiates wheels to using port maps to connect code to Roborio

  WPI_TalonFX linearActuatorA = new WPI_TalonFX(Constants.ACTUATOR_A_CAN_ID);
  WPI_TalonFX linearActuatorB = new WPI_TalonFX(Constants.ACTUATOR_B_CAN_ID);

  // Pushes new speed to intake wheel motor
  public void set(double speed) {
    linearActuatorA.set(speed);
    linearActuatorB.set(speed);
  }

  public void resetEncoders() {
    linearActuatorA.setSelectedSensorPosition(0);
    linearActuatorB.setSelectedSensorPosition(0);
  }

  public double getEncoderPosition(WPI_TalonFX talon) {
    return talon.getSelectedSensorPosition() * Constants.RAW_SENSOR_UNITS_TO_METERS;
  }

  public double getEncoderAverage() { // good enough for forwards / backwards
    double leftPos = linearActuatorA.getSelectedSensorPosition();
    double rightPos = linearActuatorB.getSelectedSensorPosition();
    return (leftPos + rightPos) / 2 * Constants.RAW_SENSOR_UNITS_TO_METERS;
  }

  public static enum Mode {
    COAST,
    BRAKE
  }

  public void setMode(Mode mode) {
    switch(mode) {
      case COAST:
        linearActuatorA.setNeutralMode(NeutralMode.Coast);
        linearActuatorB.setNeutralMode(NeutralMode.Coast);
        break;
      case BRAKE:
        linearActuatorA.setNeutralMode(NeutralMode.Brake);
        linearActuatorB.setNeutralMode(NeutralMode.Brake);
        break;
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
