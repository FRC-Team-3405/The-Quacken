package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class SwitchDirectionCommand: Command() {

    init {
        println("Initializing direction switcher command")
        requires(Robot.driveTrain)
    }

    override fun execute() {
        println("Switching direction...")
        Robot.driveTrain.switchDirection()
    }

    override fun isFinished() = true
}
