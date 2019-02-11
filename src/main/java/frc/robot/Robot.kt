/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.subsystems.Accelerometer
import frc.robot.subsystems.DriveTrain

class Robot : TimedRobot() {
    private var autoSelected: String? = null
    private val mchooser = SendableChooser<String>()

    /**
     * This function is run when the robot is first started up.
     */
    override fun robotInit() {
        mchooser.setDefaultOption("Default Auto", kDefaultAuto)
        mchooser.addOption("My Auto", kCustomAuto)
        SmartDashboard.putData("Auto choices", mchooser)

        //Register permanent button commands

    }

    /**
     * This function is called every robot packet, no matter the mode.
     */
    override fun robotPeriodic() {
        SmartDashboard.updateValues()
        Scheduler.getInstance().run()
    }

    /**
     * This function is called at the beginning of the sandstorm period.
     */
    override fun autonomousInit() {
        autoSelected = mchooser.selected
        println("Auto selected: " + autoSelected!!)
    }

    /**
     * This function is called periodically during the sandstorm period.
     */
    override fun autonomousPeriodic() {
        when (autoSelected) {
            kCustomAuto -> {
            }
            kDefaultAuto -> {
            }
            else -> {
            }
        }
    }

    /**
     * This function is called at the end of the sandstorm period.
     */
    override fun teleopInit() {

    }

    /**
     * This function is called periodically during operator control.
     */
    override fun teleopPeriodic() {

    }

    /**
     * This function is run when test mode is first started.
     */
    override fun testInit() {

    }

    /**
     * This function is called periodically during test mode.
     */
    override fun testPeriodic() {

    }

    /**
     * This function is run when robot is first disabled
     */
    override fun disabledInit() {

    }

    /**
     * This function is called periodically while disabled
     */
    override fun disabledPeriodic() {

    }

    /**
     * Static members of the robot (`Subsystems`)
     */
    companion object {
        private const val kDefaultAuto = "Default"
        private const val kCustomAuto = "My Auto"

        //Drive subsystems
        val driveTrain = DriveTrain()

        //Sensor subsystems
        val builtInAccelerometer = Accelerometer()

        //Pneumatics subsystems

        //Elevator subsystems

        //Box subsystems

        //Feeder subsystems

        //Autonomous Control Subsystem

    }
}
