// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.filters;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.util.WPIUtilJNI;

/**
 * A class that limits the rate of change of an input value. Useful for implementing voltage,
 * setpoint, and/or output ramps. A slew-rate limit is most appropriate when the quantity being
 * controlled is a velocity or a voltage; when controlling a position, consider using a {@link
 * edu.wpi.first.math.trajectory.TrapezoidProfile} instead.
 * 
 * Programmer note: 98% of this file was copied from the SlewtimeToMaxer class provided by FRC. All credit to FRC. FRC is the OG :D
 */
public class AdjustableSlewRateLimiter {
  private double m_lowerBound, m_upperBound, m_timeToMax, m_range;
  private double m_prevVal;
  private double m_prevTime;

  /**
   * Creates a new SlewtimeToMaxer with the given rate limit and initial value.
   *
   * @param timeToMax The rate-of-change limit, in units per second.
   * @param initialValue The initial value of the input.
   */
  public AdjustableSlewRateLimiter(double lowerBound, double upperBound, double timeToMax, double initialValue) {
    m_lowerBound = lowerBound;
    m_upperBound = upperBound;
    m_range = upperBound - lowerBound;  
    m_timeToMax = timeToMax;
    m_prevVal = initialValue;
    m_prevTime = WPIUtilJNI.now() * 1e-6;
  }

  /**
   * Creates a new SlewtimeToMaxer with the given rate limit and an initial value of zero.
   *
   * @param timeToMax The rate-of-change limit, in units per second.
   */
  public AdjustableSlewRateLimiter(double timeToMax) {
    this(-1, 1, timeToMax, 0);
  }

  /**
   * Filters the input to limit its slew rate.
   *
   * @param input The input value whose slew rate is to be limited.
   * @return The filtered value, which will not change faster than the slew rate.
   */
  public double calculate(double input) {
      double currentTime = WPIUtilJNI.now() * 1e-6;
      double elapsedTime = currentTime - m_prevTime;
      double rateCap = elapsedTime / m_timeToMax * m_range;
      if (elapsedTime > m_timeToMax * (input - m_prevVal) / m_range) {
        m_prevVal = input;
      } else {
        m_prevVal += MathUtil.clamp(input - m_prevVal, -rateCap, rateCap);
      }
      m_prevVal = MathUtil.clamp(m_prevVal, m_lowerBound, m_upperBound);
      m_prevTime = currentTime;
      return m_prevVal;
  }

  /**
   * Resets the slew rate limiter to the specified value; ignores the rate limit when doing so.
   *
   * @param value The value to reset to.
   */
  public void reset(double value) {
    m_prevVal = value;
    m_prevTime = WPIUtilJNI.now() * 1e-6;
  }

  public void setTimeToMax(double value) {
    m_timeToMax = value;
  }
}