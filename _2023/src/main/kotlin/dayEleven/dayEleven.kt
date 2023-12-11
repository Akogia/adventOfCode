package dayEleven

import java.io.File
import kotlin.math.abs

fun main() {
    val fileContent = File("_2023/src/main/resources/dayEleven.txt").readText(Charsets.UTF_8).split("\n")
    // get galaxy location
    var galaxyLocation = mutableListOf<Pair<Int, Int>>()
    for ( (x, element) in fileContent.withIndex()) {
        println("$x: $element")
        for ((y, character) in element.withIndex()) {
            if (character == '#') {
                galaxyLocation.add(Pair(x, y))
            }
        }
    }
    // get rows and columns in which the galaxy is not located
    val emptyRows = fileContent.indices.filter { it -> it !in galaxyLocation.map { it.first }}.toIntArray()
    val emptyColumns = fileContent.indices.filter { it -> it !in galaxyLocation.map { it.second }.toIntArray()}

    val newGalaxyLocation = galaxyLocation.map { pair ->
        var shiftedRow = emptyRows.count { it < pair.first }
        var shiftedColumn = emptyColumns.count { it < pair.second }
        if (shiftedRow != 0 ) {
            shiftedRow *= (1000000 - 1)
        }
        if (shiftedColumn != 0 ) {
            shiftedColumn *= (1000000 - 1)
        }
        Pair((pair.first + shiftedRow).toLong(), (pair.second + shiftedColumn).toLong())
    }.toMutableList()

    var sum = 0.toLong()
    for (i in 0 until newGalaxyLocation.size) {
        for (j in i+1 until newGalaxyLocation.size) {
            val path = calculateShortestPath(newGalaxyLocation[i], newGalaxyLocation[j])
            //println("path: $path")
            sum += path
        }
    }
    println(sum)
}

fun calculateShortestPath(firstGalaxy: Pair<Long,Long>, secondGalaxy: Pair<Long, Long>): Long {
    return (abs(firstGalaxy.first - secondGalaxy.first) + abs(firstGalaxy.second - secondGalaxy.second)).toLong()
}