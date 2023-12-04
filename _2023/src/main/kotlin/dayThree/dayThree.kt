package dayThree

import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/dayThree.txt").readText(Charsets.UTF_8).split("\n")

    var sum = 0
    var number = ""
    var indicesOfNumber = mutableListOf<Pair<Int, Int>>()

    for (i in fileContent.indices) {
        for (j in fileContent[i].indices) {
            if (fileContent[i][j].isDigit()) {
                number += fileContent[i][j]
                indicesOfNumber.add(Pair(i, j))
                if (j == fileContent[i].lastIndex || !fileContent[i][j + 1].isDigit()) {
                    if (adjecentSymbol(fileContent, indicesOfNumber)) {
                        sum += number.toInt()
                    }
                    number = ""
                    indicesOfNumber = mutableListOf()
                }
            }
        }
    }

    println(sum)
}


fun adjecentSymbol(schematic: List<String>, indices: List<Pair<Int,Int>>): Boolean {
    for (pair in indices) {
        val i = pair.first
        val j = pair.second
        for (dx in -1..1) {
            for (dy in -1..1) {
                val nx = i + dx
                val ny = j + dy
                if (nx in schematic.indices && ny in schematic[i].indices && (!schematic[nx][ny].isDigit() && '.' != schematic[nx][ny])) {
                    return true
                }
            }
        }

    }
    return false
}