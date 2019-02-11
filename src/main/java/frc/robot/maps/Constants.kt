package frc.robot.maps

/**
 * Maximum motor controller output percentage
 */
const val MAX_MOTOR_SPEED = 0.7



data class Gains(val kP: Double, val kI: Double, val kD: Double, val kF: Double, val kIZone: Int, val kPeakOutput: Double)