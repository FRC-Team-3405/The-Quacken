/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.subsystems.Accelerometer
import frc.robot.subsystems.DriveTrain

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : TimedRobot() {
    private var autoSelected: String? = null
    private val mchooser = SendableChooser<String>()

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    override fun robotInit() {
        mchooser.setDefaultOption("Default Auto", kDefaultAuto)
        mchooser.addOption("My Auto", kCustomAuto)
        SmartDashboard.putData("Auto choices", mchooser)
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     *
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    override fun robotPeriodic() {

    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     *
     * You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    override fun autonomousInit() {
        autoSelected = mchooser.selected
        // autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        println("Auto selected: " + autoSelected!!)
    }

    /**
     * This function is called periodically during autonomous.
     */
    override fun autonomousPeriodic() {
        when (autoSelected) {
            kCustomAuto -> {
            }
            kDefaultAuto -> {
            }
            else -> {
            }
        }// Put custom auto code here
        // Put default auto code here
    }

    /**
     * This function is called periodically during operator control.
     */
    override fun teleopPeriodic() {

    }

    /**
     * This function is called periodically during test mode.
     */
    override fun testPeriodic() {

    }

    companion object {
        private const val kDefaultAuto = "Default"
        private const val kCustomAuto = "My Auto"

        //Drive subsystems
        val driveTrain = DriveTrain()

        //Sensor subsystems
        val builtInAccelerometer = Accelerometer()

        //Camera Subsystems
        //TODO
    }
}
