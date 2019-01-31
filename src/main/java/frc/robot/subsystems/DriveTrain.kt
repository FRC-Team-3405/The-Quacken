package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.*
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.runners.RunDriveTrainCommand
import frc.robot.utilities.*
import kotlin.math.PI

enum class Direction(val sign: Int) {
    FORWARD(1),
    BACKWARD(-1)
}

class DriveTrain: Subsystem(), ReportableSubsystem {

    private val frontRight = TalonSRX(0) //Right Bottom
    private val frontLeft = TalonSRX(1) //Left Top
    private val backLeft = TalonSRX(2) //Left Bottom (MASTER)
    private val backRight = TalonSRX(3) //Right Top (MASTER)

    var direction = Direction.FORWARD

    var turning = false
    var targetAngle = 0.0

    var drivingDistance = false
    var targetDistance = 0.0

    var heading = 0.0

    override fun initDefaultCommand() {
        defaultCommand = RunDriveTrainCommand()

//        backRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder)
//        backLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder)

        //Follow masters
        frontRight.follow(backRight)
        frontLeft.follow(backLeft)

        //Disable motor controllers
        backLeft.set(ControlMode.PercentOutput, 0.0)
        backRight.set(ControlMode.PercentOutput, 0.0)

        //Set neutral mode
        backLeft.setNeutralMode(NeutralMode.Brake)
        backRight.setNeutralMode(NeutralMode.Brake)

        //Set up quadrature encoder
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_PRIMARY, TIMEOUT_MS)
        //Set up left Talon sensor as remote for right Talon
        backRight.configRemoteFeedbackFilter(backLeft.deviceID, RemoteSensorSource.TalonSRX_SelectedSensor, REMOTE_0, TIMEOUT_MS)

        //Setting up the difference signal used for turning when driving straight
        backRight.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, TIMEOUT_MS)
        backRight.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.QuadEncoder, TIMEOUT_MS)

        //Makes the difference between the two sensors the PID of turning (while going straight)
        backRight.configSelectedFeedbackSensor(FeedbackDevice.SensorDifference, PID_TURN, TIMEOUT_MS)

        //Scales the error by a coefficient calculated here:
        /**
         * Heading units should be scaled to ~4000 per 360 deg, due to the following limitations...
         * - Target param for aux PID1 is 18bits with a range of [-131072,+131072] units.
         * - Target for aux PID1 in motion profile is 14bits with a range of [-8192,+8192] units.
         *  ... so at 3600 units per 360', that ensures 0.1 degree precision in firmware closed-loop
         *  and motion profile trajectory points can range +-2 rotations.
         */
        backRight.configSelectedFeedbackCoefficient(TURN_TRAVEL_UNITS_PER_ROTATION / ENCODER_UNITS_PER_ROTATION, PID_TURN, TIMEOUT_MS)

        //Configure output and sensor direction
        backLeft.inverted = false
        backLeft.setSensorPhase(true)
        backRight.inverted = true
        frontRight.inverted = true
        backRight.setSensorPhase(true)

        //Set status frame periods
        backRight.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, TIMEOUT_MS)
        backRight.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, TIMEOUT_MS)
        backLeft.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, TIMEOUT_MS) //Used remotely by right Talon, speed up

        //Configure neutral deadband
        backRight.configNeutralDeadband(NEUTRAL_DEADBAND, TIMEOUT_MS)
        backLeft.configNeutralDeadband(NEUTRAL_DEADBAND, TIMEOUT_MS)

        //Set the peak outputs
        backLeft.configPeakOutputForward(+1.0, TIMEOUT_MS)
        backLeft.configPeakOutputReverse(-1.0, TIMEOUT_MS)
        backRight.configPeakOutputForward(+1.0, TIMEOUT_MS)
        backRight.configPeakOutputReverse(-1.0, TIMEOUT_MS)

        //FPID Gains for turn servo
        backRight.config_kP(SLOT_TURNING, GAINS_TURNING.kP, TIMEOUT_MS)
        backRight.config_kI(SLOT_TURNING, GAINS_TURNING.kI, TIMEOUT_MS)
        backRight.config_kD(SLOT_TURNING, GAINS_TURNING.kD, TIMEOUT_MS)
        backRight.config_kF(SLOT_TURNING, GAINS_TURNING.kF, TIMEOUT_MS)
        backRight.config_IntegralZone(SLOT_TURNING, GAINS_TURNING.kIZone, TIMEOUT_MS)
        backRight.configClosedLoopPeakOutput(SLOT_TURNING, GAINS_TURNING.kPeakOutput, TIMEOUT_MS)
        backRight.configAllowableClosedloopError(SLOT_TURNING, 0, TIMEOUT_MS)

        /* 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
        val closedLoopTimeMs = 1
        backRight.configClosedLoopPeriod(0, closedLoopTimeMs, TIMEOUT_MS)
        backRight.configClosedLoopPeriod(1, closedLoopTimeMs, TIMEOUT_MS)

        /* configAuxPIDPolarity(boolean invert, int timeoutMs)
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
        backRight.configAuxPIDPolarity(true, TIMEOUT_MS)

        //Initialize
        resetEncoderCounts()
        Robot.joystick.ElevenButton.onPressed {
            println("This is Drive Straight using the auxiliary feature with the difference between two encoders to maintain current heading.")

            //Determine which slot affects which PID
            backRight.selectProfileSlot(SLOT_TURNING, PID_TURN)

            heading = backRight.getSelectedSensorPosition(1).toDouble()
        }

        Robot.joystick.NineButton.onPressed {
            driveDistance(36.0)
        }

        Robot.joystick.TenButton.onPressed {
            turnAngle(90.0)
        }

    }

    override fun report() {
        //Robot direction
        SmartDashboard.putNumber("direction", direction.sign.toDouble())

        //Software-set max speed TODO add a slider on Shuffleboard to configure
        SmartDashboard.putNumber("max_speed", MAX_SPEED)

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

        SmartDashboard.putNumber("heading", heading)
    }

    fun resetEncoderCounts() {
        backLeft.sensorCollection.setQuadraturePosition(0, TIMEOUT_MS)
        backRight.sensorCollection.setQuadraturePosition(0, TIMEOUT_MS)
    }

    fun tankDrive() {
        if(turning) {
            if(targetAngle > 0) {
                SmartDashboard.putNumber("ROTATION", calculateRotation(Math.abs(backLeft.selectedSensorPosition - backRight.selectedSensorPosition).toDouble()))
                if(calculateRotation(Math.abs(backLeft.selectedSensorPosition - backRight.selectedSensorPosition).toDouble()) + calculateRotation(Math.abs(backLeft.selectedSensorVelocity - backRight.selectedSensorVelocity).toDouble()) > targetAngle) {
                    turning = false
                } else {
                    driveSide(-MAX_SPEED * 0.75, MAX_SPEED * 0.75)
                    return
                }
            } else {
                if(calculateRotation(Math.abs(backLeft.selectedSensorPosition - backRight.selectedSensorPosition).toDouble()) + calculateRotation(Math.abs(backLeft.selectedSensorVelocity - backRight.selectedSensorVelocity).toDouble()) < targetAngle) {
                    turning = false
                    driveSide(MAX_SPEED * 0.75, -MAX_SPEED * 0.75)
                    return
                }
            }
        }
        if(drivingDistance) {
            if(calculateDistance(Math.abs(backRight.selectedSensorPosition).toDouble()) + 1.5 * calculateDistance(Math.abs(backRight.selectedSensorVelocity).toDouble()) > targetDistance) {
                drivingDistance = false
            } else {
                driveStraight(MAX_SPEED * direction.sign)
                return
            }
        }

        if(Robot.joystick.ElevenButton.get()) {
            driveStraight(MAX_SPEED * direction.sign)
            return
        }

        val leftY = Robot.joystick.leftY * MAX_SPEED
        val rightY = Robot.joystick.rightY * MAX_SPEED

        if(direction == Direction.FORWARD) {
            driveSide(powerLeft = leftY, powerRight = rightY)
        } else {
            //This is how it is supposed to be.
            driveSide(powerLeft = rightY, powerRight = leftY)
        }
    }

    fun switchDirection() {
        direction = when(direction) {
            Direction.FORWARD -> Direction.BACKWARD
            Direction.BACKWARD -> Direction.FORWARD
        }
    }

    private fun driveSide(powerLeft: Double, powerRight: Double) {
        backLeft.set(ControlMode.PercentOutput, powerLeft * direction.sign)
        backRight.set(ControlMode.PercentOutput, powerRight * direction.sign)
        frontLeft.follow(backLeft)
        frontRight.follow(backRight)
    }

    private fun driveAngle(power: Double, turnPower: Double) {
        val left: Double
        val right: Double

        if(power > 0) {
            if(turnPower > 0) {
                left = power - turnPower
                right = Math.max(power, turnPower)
            } else {
                left = Math.max(power, -turnPower)
                right = power + turnPower
            }
        } else {
            if(turnPower > 0) {
                left = -Math.max(-power, turnPower)
                right = power + turnPower
            } else {
                left = power - turnPower
                right = -Math.max(-power, -turnPower)
            }
        }

        println("Left: $left, Right: $right")
        driveSide(left, right)
    }

    fun driveStraight(power: Double) {
        backRight.set(ControlMode.PercentOutput, power, DemandType.AuxPID, heading)
        backLeft.follow(backRight, FollowerType.AuxOutput1)
    }

    fun turnAngle(degrees: Double) {
        resetEncoderCounts()
        targetAngle = degrees
        turning = true
    }

    fun calculateRotation(encoderDiff: Double): Double {
        return (encoderDiff / ENCODER_UNITS_PER_ROTATION) * 360.0
    }

    fun calculateDistance(encoderUnits: Double): Double {
        return (encoderUnits / 1440.0) * 6 * PI
    }

    fun driveDistance(inches: Double) {
        resetEncoderCounts()
        targetDistance = inches
        drivingDistance = true
    }

}
