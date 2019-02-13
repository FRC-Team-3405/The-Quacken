package frc.robot.utilities

import edu.wpi.first.wpilibj.command.Command

open class RunnerCommand(private val subsystem: ReportableSubsystem): Command() {
    init {
        this.requires(subsystem)
    }

    override fun execute() {
        subsystem.report()
    }

    override fun isFinished() = false
}