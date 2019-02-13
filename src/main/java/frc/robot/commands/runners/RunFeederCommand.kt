package frc.robot.commands.runners

import frc.robot.Robot
import frc.robot.utilities.RunnerCommand

class RunFeederCommand: RunnerCommand(Robot.feeder) {
    override fun execute() {
        super.execute()
        Robot.feeder.runFeeder()
    }
}