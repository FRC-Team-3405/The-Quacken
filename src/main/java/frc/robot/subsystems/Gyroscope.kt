package frc.robot.subsystems

import edu.wpi.first.wpilibj.AnalogGyro
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.runners.RunGyroscopeCommand
import frc.robot.utilties.ReportableSubsystem

class Gyroscope: Subsystem(), ReportableSubsystem {

   //  private val gyroscope = AnalogGyro(1)

    override fun initDefaultCommand() {
        defaultCommand = RunGyroscopeCommand()
    }
/* 
    fun initGryo() {
        gyroscope.initGyro()
    }

    fun calibrate() {
        gyroscope.calibrate()
    }

    fun reset() {
        gyroscope.reset()
    }*/

    override fun report() {
//        SmartDashboard.putNumber("gyro_angle", gyroscope.angle)
//        SmartDashboard.putNumber("gyro_offset", gyroscope.offset)
//        SmartDashboard.putNumber("gyro_rate", gyroscope.rate)
//        SmartDashboard.putNumber("gyro_pid", gyroscope.pidGet())
//        SmartDashboard.putNumber("gyro_center", gyroscope.center.toDouble())
    }

}
