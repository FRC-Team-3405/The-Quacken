package frc.robot.commands.runners

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunAccelerometerCommand: Command() {
    init {
        requires(Robot.builtInAccelerometer)
    }

    override fun isFinished() = false

    override fun execute() {
        Robot.builtInAccelerometer.report()
    }
}