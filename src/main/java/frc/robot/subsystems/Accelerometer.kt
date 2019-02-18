package frc.robot.subsystems

import edu.wpi.first.wpilibj.BuiltInAccelerometer
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.reporters.ReportAccelerometerCommand
import frc.robot.utilities.ReportableSubsystem

class Accelerometer: ReportableSubsystem() {

    private val accelerometer = BuiltInAccelerometer()

    override fun initDefaultCommand() {
        defaultCommand = ReportAccelerometerCommand()
    }

    override fun report() {
        SmartDashboard.putNumber("accelX", accelerometer.x)
        SmartDashboard.putNumber("accelY", accelerometer.y)
        SmartDashboard.putNumber("accelZ", accelerometer.z)
        SmartDashboard.updateValues()
    }
}