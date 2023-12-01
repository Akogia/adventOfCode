package dayOne

import java.io.File



fun main() {
  val fileContent = File("_2023/src/main/resources/dayOne.txt").readText(Charsets.UTF_8).split("\n")
  val numberMap = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

  val result: List<Int> = fileContent.map { str ->
    var remainingStr = str
    println(remainingStr)
    var newStr = ""
    while (remainingStr.isNotEmpty()) {
      var replaced = false
      for ((key, value) in numberMap) {
        //println("key: $key, value: $value")
        if (remainingStr.startsWith(key)) {
          newStr += value
          println("newStr: $newStr")
          remainingStr = remainingStr.drop(key.dropLast(1).length)
          replaced = true

          break
        }
      }
      if (!replaced) {
        newStr += remainingStr[0]
        remainingStr = remainingStr.drop(1)
      }
    }
    println("newStr: $newStr")
    val firstDigit = newStr.firstOrNull { it.isDigit() }?.toString() ?: ""
    val lastDigit = newStr.lastOrNull { it.isDigit() }?.toString() ?: ""
    println("mapped digits: $firstDigit $lastDigit")
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

