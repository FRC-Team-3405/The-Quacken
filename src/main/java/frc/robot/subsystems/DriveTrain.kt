package frc.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.runners.RunDriveTrainCommand

class DriveTrain: Subsystem() {

    override fun initDefaultCommand() {
        defaultCommand = RunDriveTrainCommand()
    }

}