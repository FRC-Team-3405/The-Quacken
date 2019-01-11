package frc.robot.subsystems

import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.runners.RunDriveTrainCommand
import frc.robot.utilties.ReportableSubsystem

const val MAX_SPEED = 0.7

enum class Side {
    LEFT, RIGHT
}

enum class Direction(val sign: Int) {
    FORWARD(1),
    BACKWARD(-1)
}

class DriveTrain: Subsystem(), ReportableSubsystem {

//    private val frontRight = TalonSRX(0) //Right Bottom
//    private val frontLeft = TalonSRX(1) //Left Top
//    private val backLeft = TalonSRX(2) //Left Bottom
//    private val backRight = TalonSRX(3) //Right Top
    var direction = Direction.FORWARD

    override fun initDefaultCommand() {
        defaultCommand = RunDriveTrainCommand()
    }

    override fun report() {
        SmartDashboard.putNumber("direction", direction.sign.toDouble())
        SmartDashboard.putNumber("max_speed", MAX_SPEED)
        //TODO add more motor stuff once those libraries are installed.
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

        driveSide(Side.LEFT, leftOutput)
        driveSide(Side.RIGHT, rightOutput)

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
//                frontLeft.set(ControlMode.PercentOutput, power * direction.sign)
//                backLeft.set(ControlMode.PercentOutput, power * direction.sign)
            }
            Side.RIGHT -> {
//                frontRight.set(ControlMode.PercentOutput, power * -direction.sign)
//                backRight.set(ControlMode.PercentOutput, power * -direction.sign)
            }
        }
    }

}
