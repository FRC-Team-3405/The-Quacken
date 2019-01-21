/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.cameraserver.CameraServer
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.*
import frc.robot.maps.XboxMap
import frc.robot.subsystems.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        GlobalScope.launch {
            val usbCamera = CameraServer.getInstance().startAutomaticCapture()
//            val axisCamera = CameraServer.getInstance().addAxisCamera("axis-camera.local")

            usbCamera.setResolution(640, 480)
//            axisCamera.setResolution(640, 480)
        }
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
        SmartDashboard.updateValues()
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
//        gyroscope.initGryo()
//        gyroscope.reset()
    }

    /**
     * This function is called periodically during autonomous.
     */
    override fun autonomousPeriodic() {
        when (autoSelected) {
            kCustomAuto -> {
                println("Custom auto...")
            }
            kDefaultAuto -> {
                println("Default auto...")
            }
            else -> {
                println("? auto...")
            }
        }
    }

    /**
     * This function is run when teleop is first started.
     */
    override fun teleopInit() {
//        gyroscope.initGryo()
//        gyroscope.reset()


        println("Registering buttons!")
        //Register button presses
        joystick.AButton.whenPressed(SwitchDirectionCommand())
        joystick.BButton.whenPressed(ReleaseThePlateCommand())
        joystick.XButton.whenPressed(TogglePlatePuncherCommand())
        joystick.YButton.whenPressed(TogglePlateGrabberCommand())
        joystick.LeftBumperButton.whenPressed(ShiftDownCommand())
        joystick.RightBumperButton.whenPressed(ShiftUpCommand())

    }

    /**
     * This function is called periodically during operator control.
     */
    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }

    /**
     * This function is run when test is first started.
     */
    override fun testInit() {

    }

    /**
     * This function is called periodically during test mode.
     */
    override fun testPeriodic() {

    }

    /**
     * This function is run when robot is first disabled.
     */
    override fun disabledInit() {

    }

    /**
     * This function is called periodically while disabled.
     */
    override fun disabledPeriodic() {

    }

    companion object {
        private const val kDefaultAuto = "Default"
        private const val kCustomAuto = "My Auto"

        val joystick = XboxMap.Controller(Joystick(0))

        //Drive subsystems
        val driveTrain = DriveTrain()
        val pneumatics = Pneumatics()
        val feeder = Feeder()

        //Sensor subsystems
        val builtInAccelerometer = Accelerometer()
        val gyroscope = Gyroscope()

        //Camera Subsystems
        //TODO
    }
}
