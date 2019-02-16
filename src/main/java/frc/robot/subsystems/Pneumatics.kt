package frc.robot.subsystems

import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.runners.RunPneumaticsCommand
import frc.robot.maps.RobotMap.SHIFTER_IN
import frc.robot.maps.RobotMap.SHIFTER_OUT
import frc.robot.utilities.PneumaticState
import frc.robot.utilities.ReportableSubsystem
import frc.robot.utilities.TwoStatePneumatic

class Pneumatics: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunPneumaticsCommand()
    }

    private val shifter = TwoStatePneumatic(DoubleSolenoid(SHIFTER_OUT, SHIFTER_IN), "shifter")

    fun shiftHighGear() {
        shifter.setState(PneumaticState.FORWARD)
    }

    fun shiftLowGear() {
        shifter.setState(PneumaticState.BACKWARD)
    }

    fun toggleShift() {
        shifter.toggleState()
    }

    override fun report() {
        val gear = when(shifter.solenoidState) {
            DoubleSolenoid.Value.kForward -> "High"
            DoubleSolenoid.Value.kOff -> "Unknown"
            DoubleSolenoid.Value.kReverse -> "Low"
        }
        SmartDashboard.putString("Gear", gear)
    }
}