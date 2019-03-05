package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunDriveTrainCommand: Command() {
    init {
        requires(Robot.driveTrain)
    }

    override fun execute() {
        Robot.driveTrain.report()
        Robot.driveTrain.drive()
    }

    override fun interrupted() {
        println("Normal Drive Train interrupted")
    }

    override fun isInterruptible() = true

    override fun isFinished() = false
}
