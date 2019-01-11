package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class TogglePlateGrabberCommand: Command() {
    init {
        println("Initializing grabber command")
        requires(Robot.pneumatics)
    }

    override fun execute() {
        println("Toggling plate grabber...")
        Robot.pneumatics.plateGrabber.toggleState()
    }

    override fun isFinished() = true
}
