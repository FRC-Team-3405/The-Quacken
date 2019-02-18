package frc.robot.subsystems

import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.runners.RunPneumaticsCommand
import frc.robot.maps.RobotMap.COMPRESSOR_PORT
import frc.robot.maps.RobotMap.SHIFTER_IN
import frc.robot.maps.RobotMap.SHIFTER_OUT
import frc.robot.utilities.PneumaticState
import frc.robot.utilities.ReportableSubsystem
import frc.robot.utilities.TwoStatePneumatic

class Pneumatics: ReportableSubsystem() {
    override fun initDefaultCommand() {
        defaultCommand = RunPneumaticsCommand()
        compressor.start()
    }

    private val compressor = Compressor(COMPRESSOR_PORT)
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
        SmartDashboard.putNumber("Compressor Current", compressor.compressorCurrent)
        SmartDashboard.putBoolean("Compressor Enabled", compressor.enabled())
        SmartDashboard.putBoolean("Pressure Switch Value", compressor.pressureSwitchValue)
        SmartDashboard.putBoolean("Fault: Compressor current too high (If true, compressor drive is disabled)", compressor.compressorCurrentTooHighFault)
        SmartDashboard.putBoolean("Sticky Fault: Compressor current too high (If true, compressor is disabled)", compressor.compressorCurrentTooHighStickyFault)
        SmartDashboard.putBoolean("Fault: Compressor is shorted", compressor.compressorShortedFault)
        SmartDashboard.putBoolean("Sticky Fault: Compressor is shorted", compressor.compressorShortedStickyFault)
        SmartDashboard.putBoolean("Fault: Compressor is not connected or current is too low", compressor.compressorNotConnectedFault)
        SmartDashboard.putBoolean("Sticky Fault: Compressor is not connected or current is too low", compressor.compressorNotConnectedStickyFault)
    }
}