package dayTwelve

import java.io.File

fun main () {
    val fileContent = File("_2023/src/main/resources/dayTwelve.txt").readText(Charsets.UTF_8).split("\n")
    var mis = 0L
    for (instruction in fileContent) {
        val (line, springs) = instruction.split(" ")
        val springsArray = springs.split(",").map { it.toInt() }.toIntArray()
        mis += generateAndCheckCombinations(line, springsArray)
    }
    println("mis: $mis")
}

fun generateAndCheckCombinations(input: String, springs: IntArray): Long {
    if (!input.contains("?")) {
        return if (compareString(input.split(".").filter { it.isNotEmpty() }, springs)) 1 else 0
    }

    val dotCombination = input.replaceFirst("?", ".")
    val hashCombination = input.replaceFirst("?", "#")

    return generateAndCheckCombinations(dotCombination, springs) + generateAndCheckCombinations(hashCombination, springs)
}

fun compareString(currentLine: List<String>, springs: IntArray): Boolean {
    if (currentLine.size != springs.size) return false
    for ((i, line) in currentLine.withIndex()) {
        if (line.length != springs[i]) {
            return false
        }
    }
    return true
}
