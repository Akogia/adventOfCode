package dayThree

import java.io.File

var sum = 0
var number = ""
var indicesOfNumber = mutableListOf<Pair<Int, Int>>()
// first Int is the indices of nx and ny, second Int is the number
var gear = mutableMapOf<Int, Int>()

fun main() {
    val fileContent = File("_2023/src/main/resources/dayThree.txt").readText(Charsets.UTF_8).split("\n")



    for (i in fileContent.indices) {
        for (j in fileContent[i].indices) {
            if (fileContent[i][j].isDigit()) {
                number += fileContent[i][j]
                indicesOfNumber.add(Pair(i, j))
                if (j == fileContent[i].lastIndex || !fileContent[i][j + 1].isDigit()) {
                    if(checkAdjecentSymbol(fileContent, indicesOfNumber, number)) {
                        println("number: $number, indicesOfNumber: $indicesOfNumber")
                    }
                    number = ""
                    indicesOfNumber = mutableListOf()
                }
            }
        }
    }

    println(sum)
}


fun checkAdjecentSymbol(schematic: List<String>,
                        indices: List<Pair<Int, Int>>,
                        number: String): Boolean {
    for (pair in indices) {
        val i = pair.first
        val j = pair.second
        for (dx in -1..1) {
            for (dy in -1..1) {
                val nx = i + dx
                val ny = j + dy
                if (nx in schematic.indices && ny in schematic[i].indices && (!schematic[nx][ny].isDigit() && '.' != schematic[nx][ny])) {
                    if ('*' == schematic[nx][ny]) {
                        if (!gear.containsKey("$nx$ny".toInt())) {
                            gear["$nx$ny".toInt()] = number.toInt()
                            println("Adding: nx: $nx, ny: $ny, number: $number, gear: $gear")
                            return true
                        } else if (gear["$nx$ny".toInt()] != null) {
                            sum += number.toInt().times(gear["$nx$ny".toInt()]!!)
                            println("multiplying: nx: $nx, ny: $ny, number: $number, gear: $gear, sum: $sum, gear[$nx$ny]: ${gear["$nx$ny".toInt()]}")
                            return true
                        }
                    }
                }
            }
        }
    }
    return false
}
