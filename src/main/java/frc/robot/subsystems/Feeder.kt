package frc.robot.subsystems

import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.RunFeederCommand
import frc.robot.maps.MAX_MOTOR_SPEED
import frc.robot.maps.RobotMap.FEEDER_MOTOR_2_PORT
import frc.robot.maps.RobotMap.FEEDER_MOTOR_PORT
import frc.robot.maps.XboxMap
import frc.robot.utilities.ReportableSubsystem

class Feeder: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunFeederCommand()
        feederMotor.isSafetyEnabled = true
        secondaryFeederMotor.isSafetyEnabled = true
    }

    private val feederMotor = Spark(FEEDER_MOTOR_PORT)
    private val secondaryFeederMotor = Spark(FEEDER_MOTOR_2_PORT)

    fun runFeeder() {
        when(Robot.joystick.povController) {
            XboxMap.PovDirections.UP, XboxMap.PovDirections.UP_LEFT, XboxMap.PovDirections.UP_RIGHT -> {
                feederMotor.set(-MAX_MOTOR_SPEED * 0.4)
                secondaryFeederMotor.set(-MAX_MOTOR_SPEED * 0.4)
            }
            XboxMap.PovDirections.DOWN, XboxMap.PovDirections.DOWN_LEFT, XboxMap.PovDirections.DOWN_RIGHT -> {
                feederMotor.set(MAX_MOTOR_SPEED * 0.4)
                secondaryFeederMotor.set(MAX_MOTOR_SPEED * 0.4)
            }
            else -> {
                feederMotor.set(0.0)
            }
        }
    }

    override fun report() {
        SmartDashboard.putString("pov", Robot.joystick.povController.toString())
        SmartDashboard.putBoolean("Feeder Motor 1 Safety Enabled", feederMotor.isSafetyEnabled)
        SmartDashboard.putBoolean("Feeder Motor 2 Safety Enabled", secondaryFeederMotor.isSafetyEnabled)
    }

}