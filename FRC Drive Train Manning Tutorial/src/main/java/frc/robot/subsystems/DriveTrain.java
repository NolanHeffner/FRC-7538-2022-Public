// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  PWMVictorSPX left1 = new PWMVictorSPX(Constants.LEFT_1_PORT);
  PWMVictorSPX left2 = new PWMVictorSPX(Constants.LEFT_2_PORT);
  PWMVictorSPX right1 = new PWMVictorSPX(Constants.RIGHT_1_PORT);
  PWMVictorSPX right2 = new PWMVictorSPX(Constants.RIGHT_2_PORT);

  public DriveTrain() {}

  public void setLeftMotors(double speed) {
    left1.set(-speed);
    left2.set(-speed);
  }

  public void setRightMotors(double speed) {
    right1.set(speed);
    right2.set(speed);
  }

  public void stopMotors() {
    left1.set(0);
    left2.set(0);
    right1.set(0);
    right2.set(0);
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
