package frc.robot.subsystems

import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.runners.RunFeederCommand
import frc.robot.maps.RobotMap.FEEDER_MOTOR_PORT
import frc.robot.utilities.ReportableSubsystem

class Feeder: ReportableSubsystem() {

    private val feederMotor = Spark(FEEDER_MOTOR_PORT)

    override fun initDefaultCommand() {
        defaultCommand = RunFeederCommand()
    }

    override fun report() {
        //TODO
    }

    fun runFeeder() {

    }

}