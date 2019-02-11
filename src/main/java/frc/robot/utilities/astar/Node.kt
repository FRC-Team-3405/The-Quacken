package frc.robot.utilities.astar

/**
 * Created by ryanberger on 1/7/17.
 */

class Node(val location: Pair<Int, Int>, val end: Pair<Int, Int>){
    var parent: Node? = null
    val g: Int = 1
    val f: Int by lazy {
        val xdist: Int = Math.abs(location.first - end.first)
        val ydist: Int = Math.abs(location.second - end.second)
        xdist + ydist
    }
    val h: Int by lazy {
        if(location.first < 0 || location.second < 0)
            9001
        else
            g + f
    }
}