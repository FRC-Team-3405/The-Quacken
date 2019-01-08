package frc.robot.maps

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton

object JoystickMap {

    //Buttons
    const val triggerButton = 1
    const val twoButton = 2
    const val threeButton = 3
    const val fourButton = 4
    const val fiveButton = 5
    const val sixButton = 6
    const val sevenButton = 7
    const val eightButton = 8
    const val nineButton = 9
    const val tenButton = 10
    const val elevenButton = 11

    //Axes
    const val xAxis = 0
    const val yAxis = 1
    const val zAxis = 2

    class Controller(private val joystick: Joystick) {
        val x
            get() = joystick.getRawAxis(JoystickMap.xAxis)
        val y
            get() = joystick.getRawAxis(JoystickMap.yAxis)
        val z
            get() = joystick.getRawAxis(JoystickMap.zAxis)

        val triggerButton = JoystickButton(joystick, JoystickMap.triggerButton)
        val twoButton = JoystickButton(joystick, JoystickMap.twoButton)
        val threeButton = JoystickButton(joystick, JoystickMap.threeButton)
        val fourButton = JoystickButton(joystick, JoystickMap.fourButton)
        val fiveButton = JoystickButton(joystick, JoystickMap.fiveButton)
        val sixButton = JoystickButton(joystick, JoystickMap.sixButton)
        val sevenButton = JoystickButton(joystick, JoystickMap.sevenButton)
        val eightButton = JoystickButton(joystick, JoystickMap.eightButton)
        val nineButton = JoystickButton(joystick, JoystickMap.nineButton)
        val tenButton = JoystickButton(joystick, JoystickMap.tenButton)
        val elevenButton = JoystickButton(joystick, JoystickMap.elevenButton)
    }

}