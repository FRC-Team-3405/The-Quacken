package frc.robot.utilities

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command
import frc.robot.maps.Gains

fun JoystickButton.onPressed(codeToExecute: () -> Unit) {
    this.whenPressed(object: Command() {
        override fun execute() {
            codeToExecute()
        }

        override fun isFinished() = true
    })
}

fun JoystickButton.onPressed(command: Command) {
    this.whenPressed(command)
}

fun TalonSRX.configGains(slotIdx: Int, gains: Gains, timeoutMs: Int) {
    this.config_kP(slotIdx, gains.kP, timeoutMs)
    this.config_kI(slotIdx, gains.kI, timeoutMs)
    this.config_kD(slotIdx, gains.kD, timeoutMs)
    this.config_kF(slotIdx, gains.kF, timeoutMs)
    this.config_IntegralZone(slotIdx, gains.kIZone, timeoutMs)
    this.configClosedLoopPeakOutput(slotIdx, gains.kPeakOutput, timeoutMs)
    this.configAllowableClosedloopError(slotIdx, gains.allowableClosedLoopError, timeoutMs)
}