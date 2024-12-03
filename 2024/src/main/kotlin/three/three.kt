package com.ipcustomer.test.three

import com.ipcustomer.test.println
import java.io.File

fun main() {
    val fileContent = File("2024/src/main/resources/input.txt").readText(Charsets.UTF_8)

    fileContent.two()
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

private fun String.two() {
    val regex = """mul\((-?\d+(\.\d+)?),(-?\d+(\.\d+)?)\)""".toRegex()
    val doRegex = """do\(\)""".toRegex()
    val dontRegex = """don't\(\)""".toRegex()

    val matchResult = regex.findAll(this)
    val doMatchResult = doRegex.findAll(this)
    val dontMatchResult = dontRegex.findAll(this)
    var result = 0

    //dontMatchResult.forEach {println(it.value)}
    //doMatchResult.forEach {println(it.value)}
    var mapMatchResult = mutableMapOf<Int,Int>()
    var doMapResult = mutableSetOf<Int>()
    var dontMapResult = mutableSetOf<Int>()
    matchResult.iterator().forEach {
        val firstNumber = it.groups[1]!!.value.toInt()
        val secondNumber = it.groups[3]!!.value.toInt()
        mapMatchResult.put(it.range.first, firstNumber*secondNumber)
    }
    dontMatchResult.iterator().forEach {
        dontMapResult.add(it.range.first)
    }
    doMatchResult.iterator().forEach {
        doMapResult.add(it.range.first)
    }
    var filteredmapMatchResult = mutableMapOf<Int,Int>()

    mapMatchResult.iterator().forEach {
        // to add to filter it shall be after do indices and before don't indices
        var shouldRemove = false

        for (dontIndex in dontMapResult) {
            // If it falls within the don't() index range
            if (dontIndex < it.key) {
                // Find the next do() index after the current don't() index
                val nextDoIndex = doMapResult.find { it > dontIndex }
                if (nextDoIndex != null && it.key < nextDoIndex) {
                    shouldRemove = true
                    break
                }
            }
        }

        // If not marked for removal, add to the filtered list
        if (!shouldRemove) {
            println("add to list ${it.value} with indices ${it.key}")
            filteredmapMatchResult[it.key] = it.value
        }
    }
    filteredmapMatchResult.iterator().forEach {result += it.value}
    println("$result")
//matchResult.println()
    // filter the result

}