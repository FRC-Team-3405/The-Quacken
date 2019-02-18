package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.VictorSPX
import edu.wpi.first.wpilibj.Spark
import frc.robot.Robot
//import frc.robot.commands.reporters.RunBoxCommand
import frc.robot.maps.RobotMap.BOX_BELT_MOTOR_PORT
import frc.robot.maps.RobotMap.BOX_MOTOR_PORT
import frc.robot.utilities.ReportableSubsystem

class Box: ReportableSubsystem() {
    override fun initDefaultCommand() {
//        defaultCommand = RunBoxCommand()
    }

    private val boxRotatorMotor = VictorSPX(BOX_MOTOR_PORT)
    private val beltMotor = Spark(BOX_BELT_MOTOR_PORT)

    override fun report() {

    }
}