package dayFour

import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/dayFour.txt").readText(Charsets.UTF_8).split("\n")
    val splitted = fileContent.map { it.split(":") }.map { it[1].split("|") }
    println("splitted: $splitted")
    var sum = 0
    for (element in splitted) {
        val numbers = element.map { it.split(" ").filter { it.isNotEmpty() }.map { it.toInt() } }
        val winningNumbers = numbers[0]
        val chosenNumbers = numbers[1]
        println("winningNumbers: $winningNumbers, chosenNumbers: $chosenNumbers")
        val commonNumbers = winningNumbers.intersect(chosenNumbers)
        println("commonNumbers: $commonNumbers")
        println("commonNumbers.size: ${commonNumbers.size}")
        sum += doubleValue(commonNumbers.size)
        println("sum: $sum")
    }
}

fun doubleValue(number: Int): Int {
    if(number == 0) {
        return 0
    }
    var value = 1
    for (i in 1..<number) {
        value *= 2
    }
    return value
}