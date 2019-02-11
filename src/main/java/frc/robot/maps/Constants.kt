package frc.robot.maps

/**
 * Maximum motor controller output percentage
 */
const val MAX_MOTOR_SPEED = 0.7

/**
 * Whether or not the right side of the robot should be inverted.
 */
const val INVERT_RIGHT = true

data class Gains(val kP: Double, val kI: Double, val kD: Double, val kF: Double, val kIZone: Int, val kPeakOutput: Double)