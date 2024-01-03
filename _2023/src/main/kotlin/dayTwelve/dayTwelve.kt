package dayTwelve

import java.io.File

fun main () {
    val fileContent = File("_2023/src/main/resources/dayTwelve.txt").readText(Charsets.UTF_8).split("\n")
    var mis = 0L
    for (instruction in fileContent) {
        val (line, springs) = instruction.split(" ")
        val springsArray = springs.split(",").map { it.toInt() }.toIntArray()
        val currentLine = line
        val combinations = generateCombinations(currentLine)
        combinations.forEach { println(it) }
        var counter = 0L
        val list = mutableListOf<List<String>>()
        combinations.forEach { comb ->
            var combos = comb.split(".").filter { it.isNotEmpty() }
            if( combos.size == springsArray.size) {
                list.add(combos)
            }
        }
        list.forEach {
            if ( compareString(it, springsArray)) {
                counter++
            }
        }
        mis += counter
    }
    println("mis: $mis")
}

fun compareString(currentLine: List<String>, springs: IntArray): Boolean {
    for ((i, line) in currentLine.withIndex()) {
        if ( line.length != springs[i]) {
            return false
        }
    }
    return true
}
fun generateCombinations(input: String): List<String> {
    if (!input.contains("?")) return listOf(input)

    val dotCombination = input.replaceFirst("?", ".")
    val hashCombination = input.replaceFirst("?", "#")

    return generateCombinations(dotCombination) + generateCombinations(hashCombination)
}
