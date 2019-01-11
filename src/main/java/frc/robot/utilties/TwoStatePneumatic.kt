package frc.robot.utilties

import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.subsystems.PneumaticState

class TwoStatePneumatic(private val doubleSolenoid: DoubleSolenoid, val name: String) {
    val solenoidState get() = doubleSolenoid.get()

    fun forward() {
        println("Pneumatic $name forward")
        checkFault()
        if(doubleSolenoid.get() != DoubleSolenoid.Value.kForward) {
            doubleSolenoid.set(DoubleSolenoid.Value.kForward)
        }
    }

    fun reverse() {
        println("Pneumatic $name reverse")
        checkFault()
        if(doubleSolenoid.get() != DoubleSolenoid.Value.kReverse) {
            doubleSolenoid.set(DoubleSolenoid.Value.kReverse)
        }
    }

    fun off() {
        println("Pneumatic $name off")
        checkFault()
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
            null -> {
                println("Error: solenoid state was null")
            }
        }
    }

    private fun checkFault() {
        SmartDashboard.putBoolean("fwd_solenoid_fault_$name", doubleSolenoid.isFwdSolenoidBlackListed)
        SmartDashboard.putBoolean("rev_solenoid_fault_$name", doubleSolenoid.isRevSolenoidBlackListed)
    }

}
