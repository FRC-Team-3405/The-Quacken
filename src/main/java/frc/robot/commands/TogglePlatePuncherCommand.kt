package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class TogglePlatePuncherCommand: Command() {
    init {
        println("Initializing puncher command")
        requires(Robot.pneumatics)
    }

    override fun execute() {
        println("Toggling plate puncher...")
        Robot.pneumatics.platePuncher.toggleState()
    }

    override fun isFinished() = true
}
