package frc.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.runners.RunDriveTrainCommand
import frc.robot.maps.INVERT_RIGHT
import frc.robot.maps.MAX_MOTOR_SPEED
import frc.robot.maps.RobotMap.BL_TALONSRX
import frc.robot.maps.RobotMap.BR_TALONSRX
import frc.robot.maps.RobotMap.FL_TALONSRX
import frc.robot.maps.RobotMap.FR_TALONSRX
import frc.robot.utilities.ReportableSubsystem

class DriveTrain: Subsystem(), ReportableSubsystem {

    private val frontRight = TalonSRX(FR_TALONSRX)
    private val frontLeft = TalonSRX(FL_TALONSRX)
    private val backRight = TalonSRX(BR_TALONSRX)
    private val backLeft = TalonSRX(BL_TALONSRX)

    var busyDoingStuff = false
    private var direction = Direction.HATCH_FORWARD


    override fun initDefaultCommand() {
        defaultCommand = RunDriveTrainCommand()

//        //Configure TalonSRX
//        frontRight.apply {
//            follow(backRight)
//            inverted = INVERT_RIGHT
//        }
//
//        frontLeft.apply {
//            follow(backLeft)
//            inverted = false
//        }
//
//        backLeft.apply {
//
//        }
//
//        backRight.apply {
//            set(ControlMode.PercentOutput, 0.0)
//            configRemoteFeedbackFilter(backLeft.deviceID, RemoteSensorSource.TalonSRX_SelectedSensor, REMOTE_0, TIMEOUT_MS)
//        }
    }

    fun drive() {
        if(!busyDoingStuff) {
            val leftY = Robot.joystick.leftY * MAX_MOTOR_SPEED
            val rightY = Robot.joystick.rightY * MAX_MOTOR_SPEED

            if(direction == Direction.HATCH_FORWARD) {
                driveSide(powerLeft = leftY, powerRight = rightY)
            } else {
                //Intentional swap
                driveSide(powerLeft = rightY, powerRight = leftY)
            }
        }
    }

    private fun driveSide(powerLeft: Double, powerRight: Double) {
        backLeft.set(ControlMode.PercentOutput, powerLeft * direction.sign)
        backRight.set(ControlMode.PercentOutput, powerRight * direction.sign)
        frontLeft.follow(backLeft)
        frontRight.follow(backRight)
    }


    override fun report() {
        //Robot direction
        SmartDashboard.putNumber("direction", direction.sign.toDouble())

        //Software-set max speed TODO add a slider on Shuffleboard to configure
        SmartDashboard.putNumber("max_speed", MAX_MOTOR_SPEED)

        //Encoder stuff
        SmartDashboard.putNumber("right_encoder_count", backRight.selectedSensorPosition.toDouble())
        SmartDashboard.putNumber("left_encoder_count", backLeft.selectedSensorPosition.toDouble())

        //Rotation values
        SmartDashboard.putNumber("right_encoder_rotations", backRight.selectedSensorPosition / 1440.0)
        SmartDashboard.putNumber("left_encoder_rotations", backLeft.selectedSensorPosition / 1440.0)

        SmartDashboard.putNumber("right_sensor_feedback", backRight.getSelectedSensorPosition(PID_TURN).toDouble())

        //Motor temperature
        SmartDashboard.putNumber("back_right_talon_temp", backRight.temperature)
        SmartDashboard.putNumber("back_left_talon_temp", backLeft.temperature)
        SmartDashboard.putNumber("front_left_talon_temp", frontLeft.temperature)
        SmartDashboard.putNumber("front_right_talon_temp", frontRight.temperature)

        //Motor inversions
        SmartDashboard.putBoolean("back_right_inverted", backRight.inverted)
        SmartDashboard.putBoolean("back_left_inverted", backLeft.inverted)
        SmartDashboard.putBoolean("front_left_inverted", frontLeft.inverted)
        SmartDashboard.putBoolean("front_right_inverted", frontRight.inverted)

        //Motor current
        SmartDashboard.putNumber("back_right_current", backRight.outputCurrent)
        SmartDashboard.putNumber("back_left_current", backLeft.outputCurrent)
        SmartDashboard.putNumber("front_left_current", frontLeft.outputCurrent)
        SmartDashboard.putNumber("front_right_current", frontRight.outputCurrent)

        //Motor voltage
        SmartDashboard.putNumber("back_right_voltage", backRight.motorOutputVoltage)
        SmartDashboard.putNumber("back_left_voltage", backLeft.motorOutputVoltage)
        SmartDashboard.putNumber("front_left_voltage", frontLeft.motorOutputVoltage)
        SmartDashboard.putNumber("front_right_voltage", frontRight.motorOutputVoltage)
    }

}

enum class Direction(val sign: Int) {
    HATCH_FORWARD(1),
    BOX_FORWARD(-1)
}