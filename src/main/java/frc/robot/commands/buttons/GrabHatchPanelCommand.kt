package frc.robot.commands.buttons

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot
import java.lang.Thread.sleep

class GrabHatchPanelCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.grab()
        sleep(250)
        Robot.pneumatics.retract()
    }

    override fun isFinished() = true
}