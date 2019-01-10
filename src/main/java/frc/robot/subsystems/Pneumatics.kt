package frc.robot.subsystems

import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.command.Subsystem
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.commands.runners.RunPneumaticsCommand
import frc.robot.utilties.ReportableSubsystem

class Pneumatics: Subsystem(), ReportableSubsystem {

    private val compressor = Compressor(0)

    val shifter = TwoStatePneumatic(DoubleSolenoid(TODO(), TODO()), "shifter")
    val plateGrabber = TwoStatePneumatic(DoubleSolenoid(TODO(), TODO()), "plategrabber")
    val platePusher = TwoStatePneumatic(DoubleSolenoid(TODO(), TODO()), "platepusher")



    override fun initDefaultCommand() {
        compressor.enabled()
        defaultCommand = RunPneumaticsCommand()
    }


    override fun report() {

    }

    class TwoStatePneumatic(private val doubleSolenoid: DoubleSolenoid, val name: String) {
        private var solenoidState = doubleSolenoid.get()

        fun forward() {
            checkFault()
            if(doubleSolenoid.get() != DoubleSolenoid.Value.kForward) {
                doubleSolenoid.set(DoubleSolenoid.Value.kForward)
            }
        }

        fun reverse() {
            checkFault()
            if(doubleSolenoid.get() != DoubleSolenoid.Value.kReverse) {
                doubleSolenoid.set(DoubleSolenoid.Value.kReverse)
            }
        }

        fun off() {
            checkFault()
            if(doubleSolenoid.get() != DoubleSolenoid.Value.kOff) {
                doubleSolenoid.set(DoubleSolenoid.Value.kOff)
            }
        }

        private fun checkFault() {
            SmartDashboard.putBoolean("fwd_solenoid_fault_$name", doubleSolenoid.isFwdSolenoidBlackListed)
            SmartDashboard.putBoolean("rev_solenoid_fault_$name", doubleSolenoid.isRevSolenoidBlackListed)
        }

    }

}