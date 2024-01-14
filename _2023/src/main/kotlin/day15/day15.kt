package day15

import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/day15.txt").readText(Charsets.UTF_8).split(",")
    val boxes = List<MutableMap<String,Int>>(256) { mutableMapOf()}
    fileContent.forEach { sequence ->
        if (sequence.endsWith("-")) {
            val label = sequence.substringBefore("-")
            boxes[label.hash()].remove(label)
        } else  {
            val label = sequence.substringBefore("=")
            boxes[label.hash()][label] = sequence.substringAfter("=").toInt()
        }
    }

    var boxValue = 0L
    boxes.forEachIndexed() {
        index, box -> boxValue += box.focus(index + 1)
    }
    println("boxValue: $boxValue")
}

// when '-' then remove the lens with the given label (if it exists)
// when '=' the followed number indicates the focal length and need to go into the relevant box
private fun Any?.println() = println(this)

private fun String.hash(): Int =
        this.fold(0) { acc, c -> ((acc + c.hashCode()) * 17 % 256) }

private fun Map<String, Int>.focus(boxIndex: Int): Long {
    var boxValue = 0L
    this.entries.toList().forEachIndexed { index, entry ->
        boxValue += boxIndex  * entry.value * (index + 1)
    }
    return boxValue
}
