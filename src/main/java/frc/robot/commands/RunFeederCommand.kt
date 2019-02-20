package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunFeederCommand: Command() {
    init {
        requires(Robot.feeder)
    }

    override fun execute() {
        Robot.feeder.report()
        Robot.feeder.runFeeder()
    }

    override fun isFinished() = false
}