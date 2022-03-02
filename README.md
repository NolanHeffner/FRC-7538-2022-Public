# TestRobot2022

## What is this project?
This FIRST Robotics project contains the code for Team 7358's robot for the 2022 FRC competition. It is free to repurpose and modify for yourself, although I'm not sure you would want to. It's in its early stages and is simple as befits our straightforward bot design.

## What does it do?
Our primary code is labelled TestDrivevA.B.C; There are two subsystems, the drive train and our intake/shooter system. The drive train operates on an arcadeDrive-esque system and the intake/shooter are controlled using the left and right triggers respectively on the XBox controller we've set up for our driver.

We don't have the encoders, sensors, gyros, and/or other hardware we would need to use PID control, so our autonomous is pretty jank and barebones. Currently we have not tested anything autonomous-related yet because we're still working on getting the 2022 bot finished - we haven't even tested all TeleOp function yet.

The other project folder is MotorTest, which is a really basic Motor subsystem hooked up to a stick axes so we could test if our old CIM motors and their PWM controllers were still operable.

## Plans for future iterations of the code
In future years we hope to incorporate encoders, kinematics, and odometry into our robot hardware and control for greater precision in autonomous mode. We're slowly recovering from COVID-19's pernicious effects on education, but it'll be a while before we attempt anything really advanced.

## Contributors:
Nolan Heffner, Emma, Jessica Gay (former), Anthony, Spencer, Abdou Senghore, Jabel Senghore, and Micah
