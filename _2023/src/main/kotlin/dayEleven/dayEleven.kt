package dayEleven

import java.io.File

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
    val emptyRows = fileContent.indices.filter { it -> it !in galaxyLocation.map { it.first }.toIntArray()}
    val emptyColumns = fileContent.indices.filter { it -> it !in galaxyLocation.map { it.second }.toIntArray()}
    //println("rows: $emptyRows")
    //println("columns: $emptyColumns")

    //println("galaxy: $galaxyLocation")

    galaxyLocation = galaxyLocation.map { pair ->
        val shiftedRow = emptyRows.count { it < pair.first }
        val shiftedColumn = emptyColumns.count { it < pair.second }
        Pair(pair.first + shiftedRow, pair.second + shiftedColumn)
    }.toMutableList()

    //println("after shifting :galaxyLocation $galaxyLocation")
    // iterate through each galaxy and calculate the shortest path to the other galaxies
    var sum = 0
    var test = 0
    for (i in 0 until galaxyLocation.size) {
        var shortestPath = Int.MAX_VALUE

        for (j in 0 until galaxyLocation.size) {
            if (i == j) {
                continue
            }
            println("galaxy ${galaxyLocation[i]} to galaxy ${galaxyLocation[j]}")
            val path = calculateShortestPath(galaxyLocation[i], galaxyLocation[j])
            println("path: $path")
            test += path
            if (path < shortestPath) {
                shortestPath = path
            }
        }
        sum += shortestPath
    }
    println(sum)
    println(test/2)
}

fun calculateShortestPath(firstGalaxy: Pair<Int,Int>, secondGalaxy: Pair<Int, Int>): Int {
    return Math.abs(firstGalaxy.first - secondGalaxy.first) + Math.abs(firstGalaxy.second - secondGalaxy.second)
}