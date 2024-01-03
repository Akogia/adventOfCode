package day13

import java.io.File

fun main () {
    val fileContent = File("_2023/src/main/resources/day13.txt").readText(Charsets.UTF_8).split("\n")
    val (height, width) = Pair(fileContent.size, fileContent[0].length)

    // row, column
    fileContent.forEach(::println)
    var roundRocks = fileContent.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, value ->
            if (value == 'O') Pair(rowIndex, columnIndex) else null
        }
    }.flatten().filterNotNull().toSet()

    var cubeShapedRocks = fileContent.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, value ->
            if (value == '#') Pair(rowIndex, columnIndex) else null
        }
    }.flatten().filterNotNull().toSet()

    var result = tilt('N', roundRocks, cubeShapedRocks)
    var roundRocksNew = result.minus(cubeShapedRocks)
    printBoard(height, width, roundRocksNew, cubeShapedRocks)

    val weight = calculateWeight(roundRocksNew, height, width)
    println("weight: $weight")
}

fun tilt(direction: Char, roundRocks: Set<Pair<Int,Int>>, cubeShapedRock: Set<Pair<Int,Int>>): Set<Pair<Int,Int>>{
    var nearestStone = listOf(roundRocks, cubeShapedRock).flatten()
    return when (direction) {
        // when direction is tilted to north, the north's element is taken first
        'N' -> {
            val northList = roundRocks.toList().sortedBy { it.first }
            println(northList)
            northList.forEach { rock ->
                val nextLowerPair = nearestStone.filter { it.first < rock.first && it.second == rock.second}.maxByOrNull { it.first }
                nearestStone = if (nextLowerPair != null) {
                    nearestStone.minus(rock).plus(Pair(nextLowerPair.first + 1, rock.second))
                } else {
                    nearestStone.minus(rock).plus(Pair(0, rock.second))
                }
            }
            println("nearestStoneSize: ${nearestStone.size}")
        return nearestStone.toSet()

        }
       'S' -> {
           val southList = roundRocks.toList().sortedByDescending{ it.first }
           println(southList)
           southList.forEach { rock ->
               val nextLowerPair = nearestStone.filter { it.first > rock.first }.minByOrNull { it.first }
               nearestStone = if (nextLowerPair != null) {
                   nearestStone.minus(rock).plus(Pair(nextLowerPair.first + 1, rock.second))
               } else {
                   nearestStone.minus(rock).plus(Pair(0, rock.second))
               }
           }
           return nearestStone.toSet()
       }
//        'E' -> Pair(roundRock.first, roundRock.second - 1)
//        'W' -> Pair(roundRock.first, roundRock.second + 1)
        else -> throw IllegalStateException("Invalid direction: $direction")
    }
}

fun calculateWeight(roundRocks: Set<Pair<Int,Int>>, height: Int, width: Int): Long {
    var weight = 0L
    for (row in 0 until height) {
        val rocksInRow = roundRocks.count { it.first == row }
        weight += rocksInRow * (height - row)
    }
    return weight
}

fun printBoard(height: Int, width: Int, roundRocks: Set<Pair<Int,Int>>, cubeShapedRocks: Set<Pair<Int,Int>>) {
    for (row in 0 until height) {
        for (column in 0 until width) {
            if (roundRocks.contains(Pair(row, column))) {
                print("O")
            } else if (cubeShapedRocks.contains(Pair(row, column))) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
}



