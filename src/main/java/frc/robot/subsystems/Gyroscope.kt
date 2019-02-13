package frc.robot.subsystems

import frc.robot.commands.runners.RunGyroscopeCommand
import frc.robot.utilities.ReportableSubsystem

class Gyroscope: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunGyroscopeCommand()
    }

    override fun report() {

    }
}