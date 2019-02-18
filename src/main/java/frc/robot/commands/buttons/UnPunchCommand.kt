package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class UnPunchCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.unPunch()
    }

    override fun isFinished() = true
}