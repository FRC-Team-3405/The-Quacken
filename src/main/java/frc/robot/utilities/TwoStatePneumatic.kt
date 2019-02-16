package frc.robot.utilities

import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard

class TwoStatePneumatic(private val doubleSolenoid: DoubleSolenoid, val name: String) {
    val solenoidState: DoubleSolenoid.Value
        get() = doubleSolenoid.get()

    private fun forward() {
        println("Pneumatic $name forward")
        report()
        if(doubleSolenoid.get() != DoubleSolenoid.Value.kForward) {
            doubleSolenoid.set(DoubleSolenoid.Value.kForward)
        }
    }

    private fun reverse() {
        println("Pneumatic $name reverse")
        report()
        if(doubleSolenoid.get() != DoubleSolenoid.Value.kReverse) {
            doubleSolenoid.set(DoubleSolenoid.Value.kReverse)
        }
    }

    private fun off() {
        println("Pneumatic $name off")
        report()
        if(doubleSolenoid.get() != DoubleSolenoid.Value.kOff) {
            doubleSolenoid.set(DoubleSolenoid.Value.kOff)
        }
    }

    fun setState(pneumaticState: PneumaticState) {
        when(pneumaticState) {
            PneumaticState.BACKWARD -> {
                reverse()
            }
            PneumaticState.FORWARD -> {
                forward()
            }
            PneumaticState.OFF -> {
                off()
            }
        }
    }

    fun toggleState() {
        when(solenoidState) {
            DoubleSolenoid.Value.kReverse -> {
                forward()
            }
            DoubleSolenoid.Value.kForward -> {
                reverse()
            }
            DoubleSolenoid.Value.kOff -> {
                println("Warning: Solenoid state was off")
                reverse()
            }
        }
    }

    private fun report() {
        SmartDashboard.putBoolean("fwd_solenoid_fault_$name", doubleSolenoid.isFwdSolenoidBlackListed)
        SmartDashboard.putBoolean("rev_solenoid_fault_$name", doubleSolenoid.isRevSolenoidBlackListed)
    }

}

enum class PneumaticState(val kDirection: DoubleSolenoid.Value) {
    FORWARD(DoubleSolenoid.Value.kForward),
    BACKWARD(DoubleSolenoid.Value.kReverse),
    OFF(DoubleSolenoid.Value.kOff)
}