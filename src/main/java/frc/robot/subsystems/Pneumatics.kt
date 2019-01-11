package frc.robot.subsystems

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

    val shifter = TwoStatePneumatic(DoubleSolenoid(TODO(), TODO()), "shifter")
    val plateGrabber = TwoStatePneumatic(DoubleSolenoid(TODO(), TODO()), "plategrabber")
    val platePuncher = TwoStatePneumatic(DoubleSolenoid(TODO(), TODO()), "platepusher")

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
        SmartDashboard.putBoolean("compressor_pressureswitchvalue", compressor.pressureSwitchValue)
    }

}
