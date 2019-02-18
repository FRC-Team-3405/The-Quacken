package frc.robot.subsystems

//import frc.robot.commands.reporters.RunGyroscopeCommand
import edu.wpi.first.wpilibj.AnalogGyro
import edu.wpi.first.wpilibj.AnalogInput
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.maps.MAIN_GYRO_NAME
import frc.robot.maps.RobotMap.GYROSCOPE_PORT
import frc.robot.utilities.ReportableSubsystem

class Gyroscope: ReportableSubsystem() {
    override fun initDefaultCommand() {
//        defaultCommand = RunGyroscopeCommand()
        gyroscope.name = MAIN_GYRO_NAME
    }

    private val gyroscope = AnalogGyro(AnalogInput(GYROSCOPE_PORT))

    fun initGyro() {
        gyroscope.initGyro()
    }

    fun calibrate() {
        gyroscope.calibrate()
    }

    fun reset() {
        gyroscope.reset()
    }

    fun getAngle(): Double {
        return gyroscope.angle
    }

    fun getRate(): Double {
        return gyroscope.rate
    }

    override fun report() {
        SmartDashboard.putNumber("Gyroscope ${gyroscope.name} Angle", gyroscope.angle)
        SmartDashboard.putNumber("Gyroscope ${gyroscope.name} Rate", gyroscope.rate)
        SmartDashboard.putNumber("Gyroscope ${gyroscope.name} Offset", gyroscope.offset)
        SmartDashboard.putNumber("Gyroscope ${gyroscope.name} PID", gyroscope.pidGet())
        SmartDashboard.putNumber("Gyroscope ${gyroscope.name} Center", gyroscope.center.toDouble())
    }
}