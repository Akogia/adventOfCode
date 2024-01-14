package day15

import println
import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/day15.txt").readText(Charsets.UTF_8).split(",")
    println(fileContent)
    fileContent.sumOf { it.hash() }.println()
}

private fun Any?.println() = println(this)
private fun String.hash(): Int =
        this.fold(0) { acc, c -> ((acc + c.hashCode()) * 17 % 256) }
