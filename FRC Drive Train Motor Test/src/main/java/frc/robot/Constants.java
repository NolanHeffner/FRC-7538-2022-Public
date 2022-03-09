// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    // Controller ports
    public static int DRIVER_XBOX_PORT = 0;

    // Buttons are on a different enumeration
    public static int XBOX_A_BUTTON = 1;

    // Motor ports - arbitrary for now, probably will be 0 thru 7 when the robot is done tho
    public static int MOTOR_PORT_1 = 5;
    public static int MOTOR_PORT_2 = 6;
    public static int MOTOR_PORT_3 = 0;
    public static int MOTOR_PORT_4 = 0;
    
    // Motor limits
    public static double SPEED_CAP = 0.3;
    public static double LY_DEAD_ZONE = 0.65;

}
