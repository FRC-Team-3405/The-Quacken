package frc.robot.subsystems

import edu.wpi.first.wpilibj.AnalogInput
import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.runners.RunPneumaticsCommand
import frc.robot.utilties.ReportableSubsystem
import frc.robot.utilties.TwoStatePneumatic

enum class PneumaticState(val kDirection: DoubleSolenoid.Value) {
    FORWARD(DoubleSolenoid.Value.kForward),
    BACKWARD(DoubleSolenoid.Value.kReverse),
    OFF(DoubleSolenoid.Value.kOff)
}

class Pneumatics: Subsystem(), ReportableSubsystem {

    private val compressor = Compressor(0)
    private val pressureSensor = AnalogInput(0)

    val shifter = TwoStatePneumatic(DoubleSolenoid(5, 4), "shifter")
    val plateGrabber = TwoStatePneumatic(DoubleSolenoid(3, 2), "plategrabber")
    val platePuncher = TwoStatePneumatic(DoubleSolenoid(1, 0), "platepuncher")

    override fun initDefaultCommand() {
        compressor.enabled()
        defaultCommand = RunPneumaticsCommand()
    }


    //TODO test these functions
    fun shiftUp() {
        shifter.setState(PneumaticState.FORWARD)
    }

    fun shiftDown() {
        shifter.setState(PneumaticState.BACKWARD)
    }

    override fun report() {
        SmartDashboard.putString("plategrabber_direction", plateGrabber.solenoidState.toString())
        SmartDashboard.putString("platepuncher_direction", platePuncher.solenoidState.toString())
        SmartDashboard.putString("shifter_direction", shifter.solenoidState.toString())
        SmartDashboard.putBoolean("compressor_pressureswitchvalue", compressor.pressureSwitchValue)

        SmartDashboard.putNumber("Analog Pressure Sensor Voltage", pressureSensor.voltage)
        SmartDashboard.putNumber("~Pressure (PSI)", analogToUnitPSI(pressureSensor.voltage))

    }

    companion object {
        private const val SENSOR_VOLTAGE = 5.0
        private fun analogToUnitPSI(voltage: Double): Double {
            return 250 * (voltage / SENSOR_VOLTAGE) - 25
        }
    }

}
