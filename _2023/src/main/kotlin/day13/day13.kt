package day13

import println
import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/day13.txt").readText(Charsets.UTF_8).split("\n")
    val result = fileContent.collectPattern()
    result.sumOf { getMirrorPattern(it) }.println()
}

private fun getMirrorPattern (pattern: List<String>): Int =
    pattern.mirrorVerticalPattern() ?:
    pattern.mirrorHorizontalPattern() ?:
    throw Exception("No mirror pattern found")

private fun List<String>.mirrorHorizontalPattern(): Int? {
    return isMirrored(this.columnToString())?.first?.plus(1)?.times(100)
}
private fun List<String>.mirrorVerticalPattern(): Int? {
    return isMirrored(this)?.first?.plus(1)
}


private fun isMirrored(pattern: List<String>): Pair<Int,Int>? {
    val mirrorIndices = findMirrorIndices(pattern.subList(0,2))
    for (mirrorIndex in mirrorIndices) {
        if (validateMirrorIndices(mirrorIndex, pattern) != null) {
            return mirrorIndex
        }
    }
    return null
}

fun validateMirrorIndices (mirrorIndex: Pair<Int, Int>, pattern: List<String>): Pair<Int,Int>? {
    val range = createRanges(mirrorIndex.first, pattern[0].length-1)
    var smudge = 0;
    for (i in pattern.indices) {
        for (j in range) {
            if (pattern[i][j.first] != pattern[i][j.second]) {
                smudge++
            }
        }
    }
    return if (smudge == 1) mirrorIndex else null
}

private fun createRanges (start: Int, max: Int): List<Pair<Int,Int>> =
        (start downTo 0).zip((start+1)..max)

fun findMirrorIndices (pattern: List<String>): Set<Pair<Int, Int>> {
    val mirrorIndices = mutableSetOf<Pair<Int, Int>>()
    for ( s in pattern) {
        for (i in 1 until s.length/2+1) {
            val left = s.substring(0, i)
            val right = s.substring(i, i+i)
            if (left == right.reversed()) {
                mirrorIndices.add(Pair(i - 1, i))
            }
        }
        for (i in s.length-1 downTo  s.length/2+1) {
            val left = s.substring(i - (s.length - i), i)
            val right = s.substring(i, s.length)
            if (left == right.reversed()) {
                mirrorIndices.add(Pair(i - 1, i))
            }
        }
    }
    return mirrorIndices
}

private fun List<String>.columnToString(): List<String> =
        (0 until this[0].length).map { columnIndex ->
            this.map { it[columnIndex] }.joinToString("")
        }


private fun List<String>.collectPattern(): List<List<String>> {
    return this.fold(mutableListOf(mutableListOf<String>())) { acc, s ->
        if (s.isEmpty()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(s)
        }
        acc
    }
}