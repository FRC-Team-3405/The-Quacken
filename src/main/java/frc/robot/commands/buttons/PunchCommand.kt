package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class PunchCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.punch()
    }

    override fun isFinished() = true
}