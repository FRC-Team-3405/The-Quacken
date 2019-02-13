package frc.robot.utilities

import edu.wpi.first.wpilibj.command.Subsystem

abstract class ReportableSubsystem: Subsystem() {
    abstract fun report()
}