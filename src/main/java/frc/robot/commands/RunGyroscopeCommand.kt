package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunGyroscopeCommand: Command() {
    init {
        requires(Robot.gyroscope)
    }

    override fun execute() {
        Robot.gyroscope.report()
    }

    override fun interrupted() {
        println("Gyroscope interrupted...how rude")
    }

    override fun isInterruptible() = true

    override fun isFinished() = false
}