package com.ipcustomer.test.three

import com.ipcustomer.test.println
import java.io.File

fun main() {
    val fileContent = File("2024/src/main/resources/input.txt").readText(Charsets.UTF_8)

    fileContent.one()
}

private fun String.one() {
    val regex = """mul\((-?\d+(\.\d+)?),(-?\d+(\.\d+)?)\)""".toRegex()

    val matchResult = regex.findAll(this)
    var result = 0
    if (matchResult != null) {
        matchResult.iterator().forEach {
            println("${it.value}")
            val firstNumber = it.groups[1]!!.value.toInt()
            val secondNumber = it.groups[3]!!.value.toInt()
            println("$firstNumber -> $secondNumber")
            result += firstNumber * secondNumber
        }
        println("$result")
        //matchResult.println()
    } else {
        println("no matches")
    }
}