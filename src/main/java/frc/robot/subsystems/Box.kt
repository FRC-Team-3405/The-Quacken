package frc.robot.subsystems

import frc.robot.Robot
import frc.robot.commands.runners.RunBoxCommand
import frc.robot.utilities.ReportableSubsystem

class Box: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunBoxCommand()
    }

    override fun report() {

    }
}