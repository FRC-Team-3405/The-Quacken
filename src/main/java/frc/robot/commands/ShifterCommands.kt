package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class ShiftUpCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.shiftUp()
    }

    override fun isFinished() = true
}

class ShiftDownCommand: Command() {
    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        Robot.pneumatics.shiftDown()
    }

    override fun isFinished() = true
}
