package frc.robot.subsystems

import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.runners.RunFeederCommand
import frc.robot.maps.XboxMap
import frc.robot.utilties.ReportableSubsystem

class Feeder: Subsystem(), ReportableSubsystem {

    //private val feederMotor = Spark(0)

    override fun initDefaultCommand() {
        defaultCommand = RunFeederCommand()
    }

    /* fun runFeeder() {
        when(Robot.joystick.povController) {
            XboxMap.PovDirections.UP -> {
                feederMotor.set(-MAX_SPEED)
            }
            XboxMap.PovDirections.DOWN -> {
                feederMotor.set(MAX_SPEED)
            }
            else -> {
                feederMotor.set(0.0)
            }
        }
    }

    override fun report() {
        SmartDashboard.putString("pov", Robot.joystick.povController.toString())
        SmartDashboard.putBoolean("feedermotor_isalive", feederMotor.isAlive)
        SmartDashboard.putBoolean("feedermotor_safetyenabled", feederMotor.isSafetyEnabled)
    } */

}
