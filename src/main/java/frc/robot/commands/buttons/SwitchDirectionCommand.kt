package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class SwitchDirectionCommand: Command() {

    init {
        requires(Robot.driveTrain)
    }

    override fun execute() {
        Robot.driveTrain.switchDirection()
    }

    override fun isFinished() = true

}
