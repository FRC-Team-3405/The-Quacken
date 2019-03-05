package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class FollowPathCommand(val pathPath: String): Command() {
    init {
        requires(Robot.driveTrain)
    }

    fun followPath() {

    }

    override fun execute() {

    }

    override fun isFinished(): Boolean {
        return true
    }
}
