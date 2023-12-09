package dayNine

import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/dayNine.txt").readText(Charsets.UTF_8).split("\n").toMutableList()
    val sensorValues = fileContent.map { it.split(" ").map { it.toInt() }.toIntArray() }.toMutableList()
    var sum = 0.toLong()
    for (element in sensorValues) {
        val differenceList = mutableListOf<IntArray>()
        differenceList.add(element)
        val result = createDifferenceList(element,  differenceList)
        sum += extrapolate(result)
    }
    println(sum)

}

fun extrapolate(result: MutableList<IntArray>): Int {

    var lastElement = result.last().last()
    for (i in result.size - 2 downTo 0) {
        lastElement += result[i].last()
        // add lastElement to the array
        result[i][result[i].size - 1] = lastElement
    }
    return result[0].last()
}

fun createDifferenceList(sensorValues: IntArray, input:  MutableList<IntArray>): MutableList<IntArray> {
    val differenceArray = IntArray(sensorValues.size - 1)
    for (i in 0 until sensorValues.size - 1) {
        differenceArray[i] = sensorValues[i + 1] - sensorValues[i]
    }
    input.add(differenceArray)

    return if (differenceArray.all { it == 0 }) {
        input
    } else {
        createDifferenceList(differenceArray, input)
    }
}