package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class TogglePlateGrabberCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.plateGrabber.toggleState()
    }

    override fun isFinished() = true
}
