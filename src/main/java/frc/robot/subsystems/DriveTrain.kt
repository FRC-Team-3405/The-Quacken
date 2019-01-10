package frc.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.runners.RunDriveTrainCommand
import frc.robot.utilties.ReportableSubsystem

class DriveTrain: Subsystem(), ReportableSubsystem {

    override fun initDefaultCommand() {
        defaultCommand = RunDriveTrainCommand()
    }

    override fun report() {

    }

}