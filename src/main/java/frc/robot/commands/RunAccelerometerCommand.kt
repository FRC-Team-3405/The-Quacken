package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunAccelerometerCommand: Command() {
    init {
        requires(Robot.builtInAccelerometer)
    }

    override fun execute() {
        Robot.builtInAccelerometer.report()
    }

    override fun isFinished() = false
}