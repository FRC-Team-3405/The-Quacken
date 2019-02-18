package frc.robot.subsystems

import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.maps.RobotMap.COMPRESSOR_PORT
import frc.robot.maps.RobotMap.GRABBER_IN
import frc.robot.maps.RobotMap.GRABBER_OUT
import frc.robot.maps.RobotMap.PUNCHER_IN
import frc.robot.maps.RobotMap.PUNCHER_OUT
import frc.robot.maps.RobotMap.SHIFTER_IN
import frc.robot.maps.RobotMap.SHIFTER_OUT
import frc.robot.utilities.PneumaticState
import frc.robot.utilities.ReportableSubsystem
import frc.robot.utilities.TwoStatePneumatic

class Pneumatics: ReportableSubsystem() {
    override fun initDefaultCommand() {
        compressor.start()
        compressor.closedLoopControl = true
    }

    private val compressor = Compressor(COMPRESSOR_PORT)
    private val shifter = TwoStatePneumatic(DoubleSolenoid(SHIFTER_OUT, SHIFTER_IN), "shifter")
    private val puncher = TwoStatePneumatic(DoubleSolenoid(PUNCHER_OUT, PUNCHER_IN), "puncher")
    private val grabber = TwoStatePneumatic(DoubleSolenoid(GRABBER_OUT, GRABBER_IN), "grabber")

    fun shiftHighGear() {
        shifter.setState(PneumaticState.FORWARD)
        report()
    }

    fun shiftLowGear() {
        shifter.setState(PneumaticState.BACKWARD)
        report()
    }

    fun toggleShift() {
        shifter.toggleState()
        report()
    }

    fun punch() {
        puncher.setState(PneumaticState.FORWARD)
        report()
    }

    fun unPunch() {
        puncher.setState(PneumaticState.BACKWARD)
        report()
    }

    fun grab() {
        grabber.setState(PneumaticState.FORWARD)
        report()
    }

    fun retract() {
        grabber.setState(PneumaticState.BACKWARD)
        report()
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
        SmartDashboard.putBoolean("Compressor in closed loop control mode", compressor.closedLoopControl)
        SmartDashboard.putBoolean("Pressure Switch Value", compressor.pressureSwitchValue)
        SmartDashboard.putBoolean("Fault: Compressor current too high (If true, compressor drive is disabled)", compressor.compressorCurrentTooHighFault)
        SmartDashboard.putBoolean("Sticky Fault: Compressor current too high (If true, compressor is disabled)", compressor.compressorCurrentTooHighStickyFault)
        SmartDashboard.putBoolean("Fault: Compressor is shorted", compressor.compressorShortedFault)
        SmartDashboard.putBoolean("Sticky Fault: Compressor is shorted", compressor.compressorShortedStickyFault)
        SmartDashboard.putBoolean("Fault: Compressor is not connected or current is too low", compressor.compressorNotConnectedFault)
        SmartDashboard.putBoolean("Sticky Fault: Compressor is not connected or current is too low", compressor.compressorNotConnectedStickyFault)
    }
}