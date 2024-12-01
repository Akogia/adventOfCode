package dayTen

import java.io.File

enum class PoleDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST
}

fun main() {
    val fileContent = File("_2023/src/main/resources/test.txt").readText(Charsets.UTF_8).split("\n")
    val startingPoint = fileContent.indices.flatMap { y ->
        fileContent[0].indices.mapNotNull { x ->
            if (fileContent[y][x] == 'S') Pair(y,x) else null
        }
    }
    println(startingPoint)
}

//fun findLoop(startingPoint: Pair<Int, Int>, direction: PoleDirection): Int {
//    var currentPoint = startingPoint
//    var count = 0
//    while (fileContent[currentPoint.first][currentPoint.second] != 'E') {
//        count += 1
//        currentPoint = when (fileContent[currentPoint.first][currentPoint.second]) {
//            'N' -> Pair(currentPoint.first - 1, currentPoint.second)
//            'S' -> Pair(currentPoint.first + 1, currentPoint.second)
//            'W' -> Pair(currentPoint.first, currentPoint.second - 1)
//            'E' -> Pair(currentPoint.first, currentPoint.second + 1)
//            else -> throw IllegalArgumentException("Invalid character")
//        }
//    }
//    return count
//}