package frc.robot.utilities.astar

import java.util.*

/**
 * Created by ryanberger on 1/7/17.
 */

typealias Maze = MutableList<MutableList<Int>>



class AStar(private val start: Pair<Int, Int>, private val end: Pair<Int, Int>, private val maze: Maze) {
    // Things that we are looking through
    private val closedSet: MutableSet<Node> = mutableSetOf()
    // Current node that we are looking at
    private val openSet: MutableList<Node> = mutableListOf()
    private val path: MutableList<Pair<Int, Int>> = mutableListOf()

    fun solve(): Long{
        val startTime: Long = Date().time

        openSet.add(Node(start, end))
        while (true){

            var currentNode: Node = openSet.first()
            (1 until openSet.size)
                    .asSequence()
                    .filter { openSet[it].h <= currentNode.h && openSet[it].f > currentNode.f }
                    .forEach { currentNode = openSet[it] }
            closedSet.add(currentNode)
            openSet.remove(currentNode)

            if(currentNode.location == end){
                retrace(currentNode)
                val endTime = Date().time
                path.reverse()
                printSolution(path, maze)
                return endTime - startTime
            }

            val siblings: Set<Node> = getSiblings(currentNode)
            for(node in siblings){
                node.parent = currentNode
                if (!openSet.any { it.location == node.location }) {
                    openSet.add(node)
                }
            }

        }
    }

    private fun getSiblings(a: Node): Set<Node>{
        val ret: MutableSet<Node> = mutableSetOf()
        // Search left
        if (a.location.second - 1 >= 0 && maze[a.location.first][a.location.second - 1] != 0){
            val n = Node(Pair(a.location.first, a.location.second - 1), end)
            if(closedSet.none { it.location == n.location })
                ret.add(n)
        }
        // Search right
        if (a.location.second + 1 <= maze[0].size - 1 && maze[a.location.first][a.location.second + 1] != 0){
            val n = Node(Pair(a.location.first, a.location.second + 1), end)
            if(closedSet.none { it.location == n.location })
                ret.add(n)
        }
        // Search behind
        if(a.location.first -1 >= 0 && maze[a.location.first - 1][a.location.second] != 0){
            val n = Node(Pair(a.location.first - 1, a.location.second), end)
            if(closedSet.none { it.location == n.location })
                ret.add(n)
        }
        // Search in front
        if(a.location.first + 1 <= maze[0].size - 1 && maze[a.location.first + 1][a.location.second] != 0){
            val n = Node(Pair(a.location.first + 1, a.location.second), end)
            if(closedSet.none { it.location == n.location })
                ret.add(n)
        }

        return ret.toSet()
    }





    private fun retrace(a: Node){
        path.add(a.location)
        if(a.parent !== null){
            retrace(a.parent!!)
        }
    }
}

fun printSolution(path: MutableList<Pair<Int, Int>>, maze: MutableList<MutableList<Int>>){
    path.forEach {
        when(it){
            path.first() -> maze[it.first][it.second] = 2
            path.last() -> maze[it.first][it.second] = 3
            else -> maze[it.first][it.second] = 4
        }
    }



    maze.forEach{
        it.forEach {
            when(it){
                4 -> print("\u001B[31m*\u001B[0m ")
                0 -> print("\u001B[33m$it\u001B[0m ")
                1 -> print("\u001B[34m$it\u001B[0m ")
                2 -> print("\u001B[31mS\u001B[0m ")
                3 -> print("\u001B[31mE\u001B[0m ")
            }
        }
        print("\n")
    }

}

fun solve(start: Pair<Int, Int>, end: Pair<Int, Int>, maze: Maze): Long {
    val closedSet: MutableSet<Node> = mutableSetOf()
    // Current node that we are looking at
    val openSet: MutableList<Node> = mutableListOf()
    val path: MutableList<Pair<Int, Int>> = mutableListOf()

    val startTime: Long = Date().time

    openSet.add(Node(start, end))
    while (true) {

        var currentNode: Node = openSet.first()
        (1 until openSet.size)
                .asSequence()
                .filter { openSet[it].h <= currentNode.h && openSet[it].f > currentNode.f }
                .forEach { currentNode = openSet[it] }
        closedSet.add(currentNode)
        openSet.remove(currentNode)

        if(currentNode.location == end){
            retrace(currentNode, path)
            val endTime = Date().time
            path.reverse()
            printSolution(path, maze)
            return endTime - startTime
        }

        val siblings: Set<Node> = getSiblings(currentNode, closedSet, end)
        for(node in siblings){
            node.parent = currentNode
            if (!openSet.any { it.location == node.location }) {
                openSet.add(node)
            }
        }

    }

}

fun getSiblings(a: Node, closedSet: Set<Node>, end: Pair<Int, Int>): Set<Node> {
    val ret: MutableSet<Node> = mutableSetOf()
    // Search left
    if (a.location.second - 1 >= 0 && maze[a.location.first][a.location.second - 1] != 0){
        val n = Node(Pair(a.location.first, a.location.second - 1), end)
        if(closedSet.none { it.location == n.location })
            ret.add(n)
    }
    // Search right
    if (a.location.second + 1 <= maze[0].size - 1 && maze[a.location.first][a.location.second + 1] != 0){
        val n = Node(Pair(a.location.first, a.location.second + 1), end)
        if(closedSet.none { it.location == n.location })
            ret.add(n)
    }
    // Search behind
    if(a.location.first -1 >= 0 && maze[a.location.first - 1][a.location.second] != 0){
        val n = Node(Pair(a.location.first - 1, a.location.second), end)
        if(closedSet.none { it.location == n.location })
            ret.add(n)
    }
    // Search in front
    if(a.location.first + 1 <= maze[0].size - 1 && maze[a.location.first + 1][a.location.second] != 0){
        val n = Node(Pair(a.location.first + 1, a.location.second), end)
        if(closedSet.none { it.location == n.location })
            ret.add(n)
    }
    return ret.toSet()
}

fun retrace(a: Node, path: MutableList<Pair<Int, Int>>) {
    path.add(a.location)
    if(a.parent !== null){
        retrace(a.parent!!, path)
    }
}