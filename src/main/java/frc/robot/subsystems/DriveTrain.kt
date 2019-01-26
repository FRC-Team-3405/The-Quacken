package frc.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.runners.RunDriveTrainCommand
import frc.robot.utilties.ReportableSubsystem
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration

const val MAX_SPEED = 0.7

enum class Side {
    LEFT, RIGHT
}

enum class Direction(val sign: Int) {
    FORWARD(1),
    BACKWARD(-1)
}

class DriveTrain: Subsystem(), ReportableSubsystem {

    private val frontRight = TalonSRX(0) //Right Bottom
    private val frontLeft = TalonSRX(1) //Left Top
    private val backLeft = TalonSRX(2) //Left Bottom
    private val backRight = TalonSRX(3) //Right Top
    var direction = Direction.FORWARD

    override fun initDefaultCommand() {
        defaultCommand = RunDriveTrainCommand()

        backRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder)
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder)
    }

    override fun report() {
        //Motor direction (possibly replace with motor inversion?)
        SmartDashboard.putNumber("direction", direction.sign.toDouble())

        //Software-set max speed TODO add a slider on Shuffleboard to configure
        SmartDashboard.putNumber("max_speed", MAX_SPEED)

        //Encoder stuff
        SmartDashboard.putNumber("right_encoder_count", backRight.selectedSensorPosition.toDouble())
        SmartDashboard.putNumber("left_encoder_count", backLeft.selectedSensorPosition.toDouble())

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

    fun tankDrive() {
        val leftY = Robot.joystick.leftY * MAX_SPEED
        val rightY = Robot.joystick.rightY * MAX_SPEED

        var maxOutput = 0.9
        if(Robot.joystick.RightLowerBumperButton.get()) {
            maxOutput += .1
        } else if(Robot.joystick.LeftLowerBumperButton.get()) {
            maxOutput -= .3
        }

        val leftOutput = leftY * maxOutput
        val rightOutput = rightY * maxOutput

        if(direction == Direction.FORWARD) {
            driveSide(Side.LEFT, leftOutput)
            driveSide(Side.RIGHT, rightOutput)
        } else {
            //This is how it is supposed to be.
            driveSide(Side.RIGHT, leftOutput)
            driveSide(Side.LEFT, rightOutput)
        }

    }

    fun switchDirection() {
        direction = when(direction) {
            Direction.FORWARD -> Direction.BACKWARD
            Direction.BACKWARD -> Direction.FORWARD
        }
    }

    private fun driveSide(side: Side, power: Double) {
        when(side) {
            Side.LEFT -> {
               frontLeft.set(ControlMode.PercentOutput, power * direction.sign)
               backLeft.set(ControlMode.PercentOutput, power * direction.sign)
            }
            Side.RIGHT -> {
               frontRight.set(ControlMode.PercentOutput, power * -direction.sign)
               backRight.set(ControlMode.PercentOutput, power * -direction.sign)
            }
        }
    }

}
