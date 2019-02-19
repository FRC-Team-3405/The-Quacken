package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.Robot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class ReleaseThePlateCommand: Command() {

    init {
        requires(Robot.pneumatics)
    }

    override fun execute() {
        GlobalScope.launch {
//            Robot.pneumatics.plateGrabber.forward()
//            Robot.pneumatics.platePuncher.forward()
            sleep(1000)
//            Robot.pneumatics.plateGrabber.reverse()
            sleep(500)
//            Robot.pneumatics.platePuncher.reverse()
        }
    }

    override fun isFinished() = true

}