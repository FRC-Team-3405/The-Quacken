package frc.robot.subsystems

import frc.robot.commands.runners.RunElevatorCommand
import frc.robot.utilities.ReportableSubsystem

class Elevator: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunElevatorCommand()
    }

    override fun report() {

    }
}