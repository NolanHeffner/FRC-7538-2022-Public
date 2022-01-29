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
    // public static int DRIVER_JOYSTICK_PORT = 0;
    public static int DRIVER_XBOX_PORT = 0;

    // Controller button numbers (for controller mapping)
    // public static int JOYSTICK_AUXILIARY_BUTTON_1 = 0; // we aren't planning to use a joystick currently
    // public static int JOYSTICK_X_AXIS = 1;
    // public static int JOYSTICK_Y_AXIS = 2;

    // Xbox Controller button number template
    public static int XBOX_LEFT_STICK_X = 0;
    public static int XBOX_LEFT_STICK_Y = 1;
    public static int XBOX_LEFT_TRIGGER = 2;
    public static int XBOX_RIGHT_TRIGGER = 3;
    public static int XBOX_RIGHT_STICK_X = 4;
    public static int XBOX_RIGHT_STICK_Y = 5;

    // Buttons are on a different enumeration
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
    public static int LEFT_1_PORT = 0;
    public static int LEFT_2_PORT = 1;
    public static int RIGHT_1_PORT = 2;
    public static int RIGHT_2_PORT = 3;

    public static int LEFT_BELT_1_PORT = 4;
    public static int LEFT_BELT_2_PORT = 5;
    public static int RIGHT_BELT_1_PORT = 6;
    public static int RIGHT_BELT_2_PORT = 7;
    
    // Conveyer Belt Speeds - to be tweaked in testing to allow for shots from various angles and distances
    public static double LOW_BELT_SPEED = 0.3;
    public static double MEDIUM_BELT_SPEED = 0.6;
    public static double HIGH_BELT_SPEED = 1;
}
