package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class TogglePlatePuncherCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.platePuncher.toggleState()
    }

    override fun isFinished() = true
}
