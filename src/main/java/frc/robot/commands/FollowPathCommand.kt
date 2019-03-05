package frc.robot.commands

import edu.wpi.first.wpilibj.Notifier
import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot
import frc.robot.maps.ENCODER_UNITS_PER_REVOLUTION
import frc.robot.maps.MAX_ROBOT_VELOCITY
import frc.robot.maps.WHEEL_DIAMETER
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.PathfinderFRC
import jaci.pathfinder.followers.EncoderFollower

class FollowPathCommand(val pathName: String): Command() {

    //TODO may have to invert path files and take opposite of desired heading instead.

    init {
        requires(Robot.driveTrain)
        requires(Robot.gyroscope)
    }

    private lateinit var leftFollower: EncoderFollower
    private lateinit var rightFollower: EncoderFollower

    private lateinit var followerNotifier: Notifier

    private var finished = false

    override fun initialize() {
        super.initialize()

        val leftTrajectory = PathfinderFRC.getTrajectory("$pathName.left")
        val rightTrajectory = PathfinderFRC.getTrajectory(pathName)

        leftFollower = EncoderFollower(leftTrajectory)
        rightFollower = EncoderFollower(rightTrajectory)

        leftFollower.configureEncoder(Robot.driveTrain.getLeftEncoder(), ENCODER_UNITS_PER_REVOLUTION.toInt(), WHEEL_DIAMETER)
        //TODO tune the PID values on the following line!
        leftFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / MAX_ROBOT_VELOCITY, 0.0)

        rightFollower.configureEncoder(Robot.driveTrain.getRightEncoder(), ENCODER_UNITS_PER_REVOLUTION.toInt(), WHEEL_DIAMETER)
        //TODO tune the PID values on the following line!
        rightFollower.configurePIDVA(1.0, 0.0, 0.0, 1 / MAX_ROBOT_VELOCITY, 0.0)


        followerNotifier = Notifier(::followPath)
    }

    private fun followPath() {
        if (leftFollower.isFinished || rightFollower.isFinished) {
            followerNotifier.stop()
            finished = true
        } else {
            val leftSpeed = leftFollower.calculate(Robot.driveTrain.getLeftEncoder())
            val rightSpeed = rightFollower.calculate(Robot.driveTrain.getRightEncoder())

            val heading = Robot.gyroscope.getAngle()
            val desiredHeading = Pathfinder.r2d(leftFollower.heading)
            val headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading)
            val turn = 0.8 * (-1.0/80.0) * headingDifference

            Robot.driveTrain.driveSide(leftSpeed + turn, rightSpeed - turn)
        }
    }

    override fun execute() {
        println("Executing Path...")
    }

    override fun interrupted() {
        followerNotifier.stop()
        finished = true
        Robot.driveTrain.driveSide(0.0, 0.0)
    }

    override fun isFinished() = finished

}
