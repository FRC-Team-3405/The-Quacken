package frc.robot.utilities

import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command

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