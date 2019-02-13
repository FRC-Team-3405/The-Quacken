package frc.robot.commands.runners

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot

class RunAutonomousCommand: Command() {

    init {
        requires(Robot.autonomousControl)
    }

    override fun execute() {
        Robot.autonomousControl.report()
    }

    override fun isFinished() = false

}