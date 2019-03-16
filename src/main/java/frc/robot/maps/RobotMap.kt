package frc.robot.maps

object RobotMap {

    //Power
    const val POWER_DISTRO_ID = 0

    //Joysticks
    const val MAIN_JOYSTICK_PORT = 0 //XBox Controller
    const val SECONDARY_JOYSTICK_PORT = 1 //Joystick Controller

    //Sensors
    const val GYROSCOPE_PORT = 0
    const val PRESSURE_SENSOR_PORT = 1

    //Cameras

    //Drive
    const val FR_TALONSRX = 0 //Talon SRX
    const val FL_TALONSRX = 1 //Talon SRX
    const val BR_TALONSRX = 2 //Talon SRX
    const val BL_TALONSRX = 3 //Talon SRX

    //Pneumatics
    const val COMPRESSOR_PORT = 0
    const val SHIFTER_IN = 0
    const val SHIFTER_OUT = 1
    const val PUNCHER_IN = 3
    const val PUNCHER_OUT = 2
    const val GRABBER_IN = 7
    const val GRABBER_OUT = 6

    //Elevator
    const val ELEVATOR_MOTOR_PORT = 4 //Talon SRX

    //Box
    const val BOX_MOTOR_PORT = 5 //Victor SPX
    const val BOX_BELT_MOTOR_PORT = 2 //Spark

    //Feeder
    const val FEEDER_MOTOR_PORT = 0 //Spark
    const val FEEDER_MOTOR_2_PORT = 1 //Spark

}