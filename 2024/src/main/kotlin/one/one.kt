package com.ipcustomer.test.one

import java.io.File

fun main() {
    val fileContent = File("2024/src/main/resources/one.txt").readText(Charsets.UTF_8).split('\n')
    val firstRow = mutableListOf<Int>()
    val secondRow = mutableListOf<Int>()

    fileContent.forEach {
        s ->
        println(s)
        firstRow.add(s.split("\\s".toRegex()).first().toInt())
        secondRow.add(s.split("\\s".toRegex()).last().toInt())
    }

    var diff = 0

    for (i in firstRow.indices) {
        diff += Math.abs(firstRow[i] - secondRow[i])
    }

    println("first star result: $diff")

    val duplicatedSet = mutableMapOf<Int,Int>()
    var result = 0
    for (row in firstRow) {
        if(duplicatedSet.containsKey(row)) {
            result += duplicatedSet.getValue(row)
        } else {
            val numberOfAppearing = secondRow.filter { it == row }.size
            duplicatedSet[row] = row * numberOfAppearing
            result += row * numberOfAppearing
        }
    }
    println("second star result: $result")
}