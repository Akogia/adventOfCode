package dayTwelve

import java.io.File

fun main () {
    val fileContent = File("_2023/src/main/resources/dayTwelve.txt").readText(Charsets.UTF_8).split("\n").toMutableList()

    val result = fileContent.map { parseRow(it) }.map { unfold(it) }.sumOf { (line, springs) ->
        checkCombinations(line, springs)
    }
    println(result)
}
private fun parseRow(input: String): Pair<String, List<Int>> =
        input.split(" ").run {
            first() to last().split(",").map { it.toInt() }
        }

private fun unfold(input: Pair<String,List<Int>>): Pair<String,List<Int>> =
        (0..4).joinToString("?") {input.first} to (0..4).flatMap { input.second }
fun checkCombinations(
        line: String,
        springs: List<Int>,
        cache: MutableMap<Pair<String,List<Int>>,Long> = HashMap()
    ): Long {
    val key = line to springs
    cache[key]?.let {
        return it
    }
    if (line.isEmpty()) return if (springs.isEmpty()) 1 else 0

    return when (line.first()) {
        '.' -> checkCombinations(line.dropWhile { it == '.' }, springs, cache)

        '?' -> checkCombinations(line.substring(1), springs, cache) +
                checkCombinations("#${line.substring(1)}", springs, cache)

        '#' -> when {
            springs.isEmpty() -> 0
            else -> {
                val thisSprings = springs.first()
                val remainingSprings = springs.drop(1)
                if (thisSprings <= line.length && line.take(thisSprings).none { it == '.' }) {
                    when {
                        thisSprings == line.length -> if (remainingSprings.isEmpty()) 1 else 0
                        line[thisSprings] == '#' -> 0
                        else -> checkCombinations(line.drop(thisSprings + 1), remainingSprings, cache)
                    }
                } else 0
            }
        }

        else -> throw IllegalStateException("Invalid line: $line")
        }
    .apply {
            cache[key] = this
        }
    }


