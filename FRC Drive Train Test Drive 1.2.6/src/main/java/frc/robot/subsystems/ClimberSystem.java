// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSystem extends SubsystemBase {

  // Instantiates wheels to using port maps to connect code to Roborio

  WPI_VictorSPX linearActuator = new WPI_VictorSPX(Constants.ACTUATOR_CAN_ID);

  // Pushes new speed to intake wheel motor
  public void set(double speed) {
    linearActuator.set(speed);
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
