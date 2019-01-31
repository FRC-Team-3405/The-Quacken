package frc.robot.utilities

///DRIVETRAIN CONSTANTS------------------------------------------------------------------------------------------------------

/**
 * Max motor controller output percent
 */
const val MAX_SPEED = 0.7

/**
 * Error if action exceeds this timeout period
 */
const val TIMEOUT_MS = 30

/**
 * Motor neutral dead-band, set to the minimum 0.1%.
 */
const val NEUTRAL_DEADBAND = 0.001

/**
 * Primary and auxiliary PID Index
 */
const val PID_PRIMARY = 0
const val PID_TURN = 1

/**
 * Remote device index 0 (Can have up to 2 remote devices [0, 1])
 */
const val REMOTE_0 = 0

/**
 * Using the configSelectedFeedbackCoefficient() function, scale units to 3600 per rotation.
 * This is nice as it keeps 0.1 degrees of resolution, and is fairly intuitive.
 */
const val TURN_TRAVEL_UNITS_PER_ROTATION = 3600.0

/**
 * Empirically measure what the difference between encoders per 360'
 * Drive the robot in clockwise rotations and measure the units per rotation.
 * Drive the robot in counter clockwise rotations and measure the units per rotation.
 * Take the average of the two.
 */
const val ENCODER_UNITS_PER_ROTATION = 9500 //Demo was 51711 WAS 10960 THEN 9500

/**
 * Turning slot (Not entirely sure)
 */
const val SLOT_TURNING = 1

/**
 * PID values for turning. May need to be adjusted. (kP should really be ~2, but our encoders are off and the motors so consistent, we're ignoring that for now)
 */
val GAINS_TURNING = Gains(0.02, 0.0, 4.0, 0.0, 200, 1.00)


data class Gains(val kP: Double, val kI: Double, val kD: Double, val kF: Double, val kIZone: Int, val kPeakOutput: Double)
