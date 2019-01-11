package frc.robot.commands.runners

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunDriveTrainCommand: Command() {
    init {
        requires(Robot.driveTrain)
    }

    override fun execute() {
        Robot.driveTrain.tankDrive()
        Robot.driveTrain.report()
    }

    override fun isFinished() = false
}
