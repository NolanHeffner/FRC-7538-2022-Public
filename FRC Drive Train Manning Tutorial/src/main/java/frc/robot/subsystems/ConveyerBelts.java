// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyerBelts extends SubsystemBase {
  
  
  PWMVictorSPX leftBelt1 = new PWMVictorSPX(Constants.LEFT_BELT_1_PORT);
  PWMVictorSPX leftBelt2 = new PWMVictorSPX(Constants.LEFT_BELT_2_PORT);
  PWMVictorSPX rightBelt1 = new PWMVictorSPX(Constants.RIGHT_BELT_1_PORT);
  PWMVictorSPX rightBelt2 = new PWMVictorSPX(Constants.RIGHT_BELT_2_PORT);

  public void invertLeftBelts() {
    leftBelt1.setInverted(true);
    leftBelt2.setInverted(true);
  }

  public void setBeltSpeed(double speed) {
    // you have to invert the speed because it's with respect to orientation and left vs. right side are flipped 180 degrees
    leftBelt1.set(-speed);
    leftBelt2.set(-speed);
    rightBelt1.set(speed);
    rightBelt2.set(speed);
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
