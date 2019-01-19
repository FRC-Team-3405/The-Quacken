package frc.robot.commands.runners

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunPneumaticsCommand: Command() {

    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
       // Robot.pneumatics.report()
    }

    override fun isFinished() = false

}