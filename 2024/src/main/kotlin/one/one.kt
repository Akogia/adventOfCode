package com.ipcustomer.test.one

import com.ipcustomer.test.println
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

    firstRow.println()
    secondRow.println()
    firstRow.sort()
    secondRow.sort()

    var diff = 0

    for (i in firstRow.indices) {
        diff += Math.abs(firstRow[i] - secondRow[i])
    }

    println(diff)
}