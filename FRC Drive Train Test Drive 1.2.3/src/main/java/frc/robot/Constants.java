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

    // Xbox Controller button number template
    public static int XBOX_LEFT_STICK_X = 0; 
    public static int XBOX_LEFT_STICK_Y = 1; // Tank drive forward/backward
    public static int XBOX_LEFT_TRIGGER = 2; // Intake wheel control
    public static int XBOX_RIGHT_TRIGGER = 3; // Shooter wheel control
    public static int XBOX_RIGHT_STICK_X = 4; // Tank drive turning
    public static int XBOX_RIGHT_STICK_Y = 5;

    // Buttons are on a different enumeration, not currently used by our bot though
    public static int XBOX_A_BUTTON = 1;
    public static int XBOX_B_BUTTON = 2;
    public static int XBOX_X_BUTTON = 3;
    public static int XBOX_Y_BUTTON = 4;
    public static int XBOX_LEFT_BUMPER = 5;
    public static int XBOX_RIGHT_BUMPER = 6;
    public static int XBOX_SCREENIE_BUTTON = 7; // aka back button
    public static int XBOX_THREE_LINES_BUTTON = 8; // aka start button
    public static int XBOX_LEFT_STICK_BUTTON = 9; 
    public static int XBOX_RIGHT_STICK_BUTTON = 10;

    // Motor ports - arbitrary for now, probably will be 0 thru 7 when the robot is done tho
    public static int LEFT_1_PORT = 1;
    public static int LEFT_2_PORT = 2;
    public static int RIGHT_1_PORT = 3;
    public static int RIGHT_2_PORT = 4;

    public static int INTAKE_WHEEL_PORT = 4;
    public static int SHOOTER_WHEEL_PORT = 5;
    
    // Wheel speeds
    public static double FEED_SPEED = 0.4;
    public static double MAX_INTAKE_SPEED = 0.5;
    public static double DEFAULT_SHOOT_SPEED = 0.75;

    // Drive train config
    public static double MAX_DRIVE_SPEED = 1; // MAX POWAAAAAAA!!!
    public static double TIME_TO_MAX_SPEED = 0; // Skew limiters begone
    public static double TURN_FACTOR = 0.6; // Turn influence decreased
    public static double SCALING_FACTOR = MAX_DRIVE_SPEED / (1 + TURN_FACTOR);

    // Dead zones - Sets controller inputs to 0 in a certain range around 0 values to account for stick drift / accidental nudges
    public static double LY_DEADBAND, RX_DEADBAND, RT_DEADBAND = 0.15; // RT = Right trigger
}