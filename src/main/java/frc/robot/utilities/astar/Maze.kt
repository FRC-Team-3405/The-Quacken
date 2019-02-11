package frc.robot.utilities.astar

/**
 * Created by ryanberger on 1/7/17.
 */

val maze: MutableList<MutableList<Int>> = mutableListOf(
        mutableListOf( 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
        mutableListOf( 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0),
        mutableListOf( 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0),
        mutableListOf( 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0),
        mutableListOf( 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0),
        mutableListOf( 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0),
        mutableListOf( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        mutableListOf( 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1),
        mutableListOf( 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
        mutableListOf( 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0),
        mutableListOf( 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0),
        mutableListOf( 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0),
        mutableListOf( 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0),
        mutableListOf( 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0),
        mutableListOf( 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1)
)