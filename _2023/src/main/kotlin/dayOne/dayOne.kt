package dayOne

import java.io.File



fun main() {
  val fileContent = File("_2023/src/main/resources/dayOne.txt").readText(Charsets.UTF_8).split("\n")
  //val numberMap = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

  val result : List<Int> = fileContent.map { s ->
    println(s)
    val firstDigit = s.firstOrNull { it.isDigit() }?.toString() ?: ""
    val lastDigit = s.lastOrNull { it.isDigit() }?.toString() ?: ""
    println(firstDigit + lastDigit)
    (firstDigit + lastDigit).toInt()
    }
    println(result.sum())
}

fun Iterable<Int>.sum(): Int {
  var sum: Int = 0
  for (element in this) {
    sum += element
  }
  return sum
}

