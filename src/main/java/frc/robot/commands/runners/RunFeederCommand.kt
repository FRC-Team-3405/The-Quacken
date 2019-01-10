package frc.robot.commands.runners

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunFeederCommand: Command() {

    init {
        requires(Robot.feeder)
    }

    override fun execute() {
        Robot.feeder.report()
    }

    override fun isFinished() = false

}