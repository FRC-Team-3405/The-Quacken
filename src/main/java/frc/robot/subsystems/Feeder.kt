package frc.robot.subsystems

import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
//import frc.robot.commands.runners.RunFeederCommand
import frc.robot.maps.RobotMap.FEEDER_MOTOR_2_PORT
import frc.robot.maps.RobotMap.FEEDER_MOTOR_PORT
import frc.robot.utilities.ReportableSubsystem

class Feeder: ReportableSubsystem() {
    override fun initDefaultCommand() {
//        defaultCommand = RunFeederCommand()
        feederMotor.isSafetyEnabled = true
        secondaryFeederMotor.isSafetyEnabled = true
    }

    private val feederMotor = Spark(FEEDER_MOTOR_PORT)
    private val secondaryFeederMotor = Spark(FEEDER_MOTOR_2_PORT)

    override fun report() {
        SmartDashboard.putBoolean("Feeder Motor 1 Safety Enabled", feederMotor.isSafetyEnabled)
        SmartDashboard.putBoolean("Feeder Motor 2 Safety Enabled", secondaryFeederMotor.isSafetyEnabled)
    }

}