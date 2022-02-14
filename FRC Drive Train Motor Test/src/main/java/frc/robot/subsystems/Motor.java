// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;

public class Motor extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  private final WPI_TalonFX motor1 = new WPI_TalonFX(Constants.MOTOR_PORT_1);
  private final WPI_TalonFX motor2 = new WPI_TalonFX(Constants.MOTOR_PORT_2);
  private final WPI_TalonFX motor3 = new WPI_TalonFX(Constants.MOTOR_PORT_3);
  private final WPI_TalonFX motor4 = new WPI_TalonFX(Constants.MOTOR_PORT_4);

  public void setMotorSpeed(double speed) {
    motor1.set(speed);
    motor2.set(speed);
    motor3.set(speed);
    motor4.set(speed);
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
