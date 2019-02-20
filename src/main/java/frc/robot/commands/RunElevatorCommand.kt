package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunElevatorCommand: Command() {
    init {
        requires(Robot.elevator)
    }

    override fun execute() {
        Robot.elevator.driveElevator()
        Robot.elevator.report()
    }

    override fun isFinished() = false
}