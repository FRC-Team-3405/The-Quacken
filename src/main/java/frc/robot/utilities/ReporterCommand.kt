package frc.robot.utilities

import edu.wpi.first.wpilibj.command.Command

open class ReporterCommand(private val subsystem: ReportableSubsystem): Command() {
    override fun execute() {
        subsystem.report()
    }

    override fun isFinished() = false
}