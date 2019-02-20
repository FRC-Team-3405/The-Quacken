package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
import frc.robot.commands.RunElevatorCommand
import frc.robot.maps.MAX_ELEVATOR_SPEED
import frc.robot.maps.RobotMap.ELEVATOR_MOTOR_PORT
import frc.robot.utilities.ReportableSubsystem

class Elevator: ReportableSubsystem() {

    override fun initDefaultCommand() {
        defaultCommand = RunElevatorCommand()

    }

    private val elevatorMotor = TalonSRX(ELEVATOR_MOTOR_PORT)

    fun driveElevator() {
        elevatorMotor.set(ControlMode.PercentOutput, Robot.secondaryJoystick.y * MAX_ELEVATOR_SPEED)
    }

    fun setElevatorHeight(inches: Double) {

    }

    override fun report() {
        SmartDashboard.putBoolean("Elevator motor inverted", elevatorMotor.inverted)
        SmartDashboard.putNumber("Elevator motor temperature", elevatorMotor.temperature)
    }
}