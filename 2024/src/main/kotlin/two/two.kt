package com.ipcustomer.test.two

import com.ipcustomer.test.println
import java.io.File

fun main() {
    val fileContent = File("2024/src/main/resources/input.txt").readText(Charsets.UTF_8).split('\n')

    fileContent.one()
}

private fun List<String>.one() {
    var safeReports = 0
    forEach { line ->
        var listInt = line.split(' ').map { it.toInt() }
        listInt.println()

        if (isAscending(listInt)){
            println("Ascending")
            safeReports += 1
        } else if (isDescending(listInt)){
            println("Descending")
            safeReports += 1
        } else {
            println("Not valid report")
        }

    }
    println(safeReports)
}

private fun isAscending(numbers: List<Int>): Boolean {
    return numbers.zipWithNext().all { (a, b) -> b - a in 1..3 }
}

private fun isDescending(numbers: List<Int>): Boolean {
    return numbers.zipWithNext().all { (a, b) -> a - b in 1..3 }
}