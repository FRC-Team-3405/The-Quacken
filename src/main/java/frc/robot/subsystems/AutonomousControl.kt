package frc.robot.subsystems

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.robot.Robot
//import frc.robot.commands.runners.RunAutonomousCommand
import frc.robot.utilities.ReportableSubsystem
import frc.robot.utilities.onPressed
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class AutonomousControl: ReportableSubsystem() {

    override fun initDefaultCommand() {
//        defaultCommand = RunAutonomousCommand()

        Robot.joystick.AButton.onPressed {
            //Run docking routine
            println("Docking routine initiating...")
            GlobalScope.launch {
                ///TODO: These `sleep` commands represent blocking while loops that wait for the robot to hit a certain state.
                sleep(1000)
                println("Routine started")
                SmartDashboard.putBoolean("Docking", true)
                sleep(1000)
                println("Routine finishing...")
                sleep(1000)
                println("Routine complete!")
                SmartDashboard.putBoolean("Docking", false)
            }
        }
    }

    override fun report() {

    }

}