package day14

import java.io.File

fun main () {
    val fileContent = File("_2023/src/main/resources/day14.txt").readText(Charsets.UTF_8).split("\n")
    val (height, width) = Pair(fileContent.size, fileContent[0].length)

    val roundRocks = fileContent.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, value ->
            if (value == 'O') Pair(rowIndex, columnIndex) else null
        }
    }.flatten().filterNotNull().toSet()

    val cubeShapedRocks = fileContent.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, value ->
            if (value == '#') Pair(rowIndex, columnIndex) else null
        }
    }.flatten().filterNotNull().toSet()
    var weight = 0L
    var roundRocksNew = roundRocks
    var roundRocksNew1 = cycle(roundRocksNew,cubeShapedRocks, height, width)
    val seen = mutableMapOf<Int, Int>()
    var seenState = false
    var i = 1
    println("roundRocks: ${roundRocks.size}")
    while (i <= 1000000000) {
        roundRocksNew1 = cycle(roundRocksNew1,cubeShapedRocks, height, width)

        val state = roundRocksNew1.hashCode()
        if(seen.containsKey(state) && !seenState) {
            println("cycleNumber: $i")
            val cycleLength = i - seen.getValue(state)
            val cyclesRemaining = (1000000000 - i) % cycleLength
            i = 1000000000 - cyclesRemaining + 2
            println("cyclesRemaining: $cyclesRemaining")
            seenState = true
        } else {
            seen[state] = i
            i++
        }

    }


   // printBoard(height, width, roundRocksNew, cubeShapedRocks)
//    println("roundRocksNew: ${roundRocksNew.size}")
    weight = calculateWeight(roundRocksNew1, height)
    println("weightwewerw: $weight")
}

private fun cycle(roundRocksNew1: Set<Pair<Int, Int>>,cubeShapedRocks: Set<Pair<Int, Int>>, height: Int, width: Int): Set<Pair<Int, Int>> {
    var result = tilt('N', roundRocksNew1, cubeShapedRocks, height, width)
    var roundRocksNew1 = result.minus(cubeShapedRocks)


    result = tilt('W', roundRocksNew1, cubeShapedRocks, height, width)
    roundRocksNew1 = result.minus(cubeShapedRocks)

    result = tilt('S', roundRocksNew1, cubeShapedRocks, height, width)
    roundRocksNew1 = result.minus(cubeShapedRocks)

    result = tilt('E', roundRocksNew1, cubeShapedRocks, height, width)
    roundRocksNew1 = result.minus(cubeShapedRocks)
    //printBoard(height, width, roundRocksNew1, cubeShapedRocks)

    return roundRocksNew1
}

fun tilt(direction: Char, roundRocks: Set<Pair<Int,Int>>, cubeShapedRock: Set<Pair<Int,Int>>, height: Int, width: Int): Set<Pair<Int,Int>>{
    var nearestStone = listOf(roundRocks, cubeShapedRock).flatten()
    return when (direction) {
        // when direction is tilted to north, the north's element is taken first
        'N' -> {
            val northList = roundRocks.toList().sortedBy { it.first }
            northList.forEach { rock ->
                val nextLowerPair = nearestStone.filter { it.first < rock.first && it.second == rock.second}.maxByOrNull { it.first }
                nearestStone = if (nextLowerPair != null) {
                    nearestStone.minus(rock).plus(Pair(nextLowerPair.first + 1, rock.second))
                } else {
                    nearestStone.minus(rock).plus(Pair(0, rock.second))
                }
            }
        return nearestStone.toSet()

        }
       'S' -> {
           val southList = roundRocks.toList().sortedByDescending{ it.first }
           southList.forEach { rock ->
               val nextLowerPair = nearestStone.filter { it.first > rock.first && it.second == rock.second}.minByOrNull { it.first }
               nearestStone = if (nextLowerPair != null) {
                   nearestStone.minus(rock).plus(Pair(nextLowerPair.first - 1, rock.second))
               } else {
                   nearestStone.minus(rock).plus(Pair(height-1, rock.second))
               }
           }
           return nearestStone.toSet()
       }
        'E' -> {
            val eastList = roundRocks.toList().sortedByDescending{ it.second }

            eastList.forEach { rock ->
                val nextLowerPair = nearestStone.filter { it.second > rock.second && it.first == rock.first}.minByOrNull { it.second }
                nearestStone = if (nextLowerPair != null) {
                    nearestStone.minus(rock).plus(Pair(rock.first, nextLowerPair.second - 1))
                } else {
                    nearestStone.minus(rock).plus(Pair(rock.first, width-1))
                }
            }
            return nearestStone.toSet()
        }
        'W' -> {
            val westList = roundRocks.toList().sortedBy{ it.second }
            westList.forEach { rock ->
                val nextLowerPair = nearestStone.filter { it.second < rock.second && it.first == rock.first}.maxByOrNull { it.second }
                nearestStone = if (nextLowerPair != null) {
                    nearestStone.minus(rock).plus(Pair(rock.first, nextLowerPair.second + 1))
                } else {
                    nearestStone.minus(rock).plus(Pair(rock.first, 0))
                }
            }
            return nearestStone.toSet()
        }
        else -> throw IllegalStateException("Invalid direction: $direction")
    }
}

fun calculateWeight(roundRocks: Set<Pair<Int,Int>>, height: Int): Long {
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
