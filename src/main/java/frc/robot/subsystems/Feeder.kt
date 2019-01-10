package frc.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.runners.RunFeederCommand
import frc.robot.utilties.ReportableSubsystem

class Feeder: Subsystem(), ReportableSubsystem {

    override fun initDefaultCommand() {
        defaultCommand = RunFeederCommand()
    }

    override fun report() {

    }

}