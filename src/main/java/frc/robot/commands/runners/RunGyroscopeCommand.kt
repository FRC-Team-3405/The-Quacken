package frc.robot.commands.runners

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunGyroscopeCommand: Command() {

    init {
        requires(Robot.gyroscope)
    }

    override fun execute() {
        Robot.gyroscope.report()
    }

    override fun isFinished() = false
}
