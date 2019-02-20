package frc.robot.maps

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import javax.swing.AbstractButton

object XboxMap {

    //Buttons
    const val AButton = 1
    const val BButton = 2
    const val XButton = 3
    const val YButton = 4
    const val LeftBumperButton = 5
    const val RightBumperButton = 6
    const val LeftLowerBumperButton = 7
    const val RightLowerBumperButton = 8
    const val NineButton = 9
    const val TenButton = 10
    const val ElevenButton = 11
    const val LeftJoystickButton = 12
    const val RightJoystickButton = 13

    //Axes
    const val LeftXAxis = 0
    const val LeftYAxis = 1
    const val RightYAxis = 2
    const val RightXAxis = 3

    //POV
    const val PovController = 0

    class Controller(private val joystick: Joystick) {
        val rightX
            get() = joystick.getRawAxis(XboxMap.RightXAxis)

        val rightY
            get() = joystick.getRawAxis(XboxMap.RightYAxis)

        val leftX
            get() = joystick.getRawAxis(XboxMap.LeftXAxis)

        val leftY
            get() = joystick.getRawAxis(XboxMap.LeftYAxis)

        val povController
            get() = joystick.getPOV(XboxMap.PovController)

        val AButton = JoystickButton(joystick, XboxMap.AButton)
        val BButton = JoystickButton(joystick, XboxMap.BButton)
        val XButton = JoystickButton(joystick, XboxMap.XButton)
        val YButton = JoystickButton(joystick, XboxMap.YButton)
        val LeftBumperButton = JoystickButton(joystick, XboxMap.LeftBumperButton)
        val RightBumperButton = JoystickButton(joystick, XboxMap.RightBumperButton)
        val LeftLowerBumperButton = JoystickButton(joystick, XboxMap.LeftLowerBumperButton)
        val RightLowerBumperButton = JoystickButton(joystick, XboxMap.RightLowerBumperButton)
        val NineButton = JoystickButton(joystick, XboxMap.NineButton)
        val TenButton = JoystickButton(joystick, XboxMap.TenButton)
        val ElevenButton = JoystickButton(joystick, XboxMap.ElevenButton)
        val LeftJoystickButton = JoystickButton(joystick, XboxMap.LeftJoystickButton)
        val RightJoystickButton = JoystickButton(joystick, XboxMap.RightJoystickButton)
    }

    enum class PovDirections(val value: Int) {
        UP(0),
        UP_RIGHT(45),
        RIGHT(90),
        DOWN_RIGHT(135),
        DOWN(180),
        DOWN_LEFT(225),
        LEFT(270),
        UP_LEFT(315),
        NULL(-1);

        companion object {
            fun fromInt(value: Int): PovDirections {
                return when(value) {
                    0 -> UP
                    45 -> UP_LEFT
                    90 -> RIGHT
                    135 -> DOWN_RIGHT
                    180 -> DOWN
                    225 -> DOWN_LEFT
                    270 -> LEFT
                    315 -> UP_LEFT
                    else -> NULL
                }
            }
        }
    }
}