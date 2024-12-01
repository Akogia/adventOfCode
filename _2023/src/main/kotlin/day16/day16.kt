package day16

import java.io.File

var height: Int = 0
var width: Int = 0
var beamList = mutableListOf<MutableMap<Pair<Int, Int>, Direction>>()
var energized = mutableSetOf<MutableMap<Pair<Int, Int>, Direction>>()
fun main() {
    val fileContent = File("_2023/src/main/resources/test.txt").readText(Charsets.UTF_8).split("\n")
    println(fileContent[0][0])
    height = fileContent.size
    width =  fileContent[0].length
    beamList.add(mutableMapOf(Pair(0,0) to Direction.RIGHT))
    beamList[0][Pair(0,1)] = Direction.DOWN
    println("$height $width")
    while (beamList.isNotEmpty()) {
        for (beam in beamList.withIndex()) {
            if (isEnergized(beam.value)) {
                beamList.removeAt(beam.index)
                break
            } else {
                energized.add(beam.value)
            }
            val currentPosition = beam.value.entries.first().key
            val currentDirection = beam.value.entries.first().value
            when (fileContent[currentPosition.first][currentPosition.second]){
                '/' -> {
                    when (currentDirection) {
                        Direction.UP -> {
                            // beam will go right, add (x+1, y) to beam
                            beam.value[Pair(currentPosition.first, currentPosition.second)] = Direction.RIGHT
                        }
                        Direction.DOWN -> {
                            // beam will go left, add (x-1, y) to beam
                            beam.value[Pair(currentPosition.first, currentPosition.second)] = Direction.LEFT
                        }
                        Direction.LEFT -> {
                            // beam will go down, add (x, y+1) to beam
                            beam.value[Pair(currentPosition.first, currentPosition.second)] = Direction.DOWN
                        }
                        Direction.RIGHT -> {
                            // beam will go up, add (x, y-1) to beam
                            beam.value[Pair(currentPosition.first, currentPosition.second)] = Direction.UP
                        }
                    }

                }
                '\\' -> {
                    println("\\")

                }
                '|' -> {
                    println("|")

                }
                '-' -> {
                    println("-")

                }
                '.' -> {
                    println(".")
                    beam.value.entries.first().key
                }
                else -> println("Error")
            }
        }
    }
    println("energized: $energized")
}

private fun Any?.println() = println(this)

private fun withinRange(position: Pair<Int,Int>, direction: Direction): Boolean {
    return when (direction) {
        Direction.UP -> position.second > 0
        Direction.DOWN -> position.second < height
        Direction.LEFT -> position.first > 0
        Direction.RIGHT -> position.first < width
        else -> false
    }
}
private fun isEnergized(position: Map<Pair<Int, Int>, Direction>): Boolean {
    return energized.contains(position)
}

// Create Rules:
// Beam encounters / or \, it changes direction depending on the current direction
// Beam encounters | or - it continues in the same direction or splits into two beams depending on the current direction
//

class Direction private constructor(val dx: Int, val dy: Int) {
    companion object {
        val UP = Direction(0, -1)
        val DOWN = Direction(0, 1)
        val LEFT = Direction(-1, 0)
        val RIGHT = Direction(1, 0)
    }
}