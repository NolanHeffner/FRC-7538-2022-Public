// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Motor extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  
  private final PWMVictorSPX motor1 = new PWMVictorSPX(Constants.MOTOR_PORT_1);
  private final PWMVictorSPX motor2 = new PWMVictorSPX(Constants.MOTOR_PORT_2);
  // private final WPI_TalonFX motor3 = new WPI_TalonFX(Constants.MOTOR_PORT_3);
  // private final WPI_TalonFX motor4 = new WPI_TalonFX(Constants.MOTOR_PORT_4);

  public void setMotorSpeed(int motorNumber, double speed) {
    switch(motorNumber) {
      case 1:
        motor1.set(speed);
        break;
      case 2:
        motor2.set(speed);
        break;
      default:
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
