package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class GrabCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.grab()
    }

    override fun isFinished() = true
}