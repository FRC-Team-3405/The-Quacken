package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.CommandGroup
import frc.robot.Robot
import frc.robot.commands.reporters.ReportDriveTrainCommand

class RunDriveTrainCommandGroup: CommandGroup() {
    init {
        addParallel(ReportDriveTrainCommand())
        addParallel(RunDriveTrainCommand())
    }
}

class RunDriveTrainCommand: Command() {
    init {
        requires(Robot.driveTrain)
    }

    override fun execute() {
        Robot.driveTrain.drive()
    }

    override fun isFinished() = false
}