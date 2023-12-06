package daySix

import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/daySix.txt").readText(Charsets.UTF_8).split("\n")
    val splitted = fileContent.map { it.split(":") }
    val time = splitted[0][1].trim().split("\\s+".toRegex()).map { it.toInt() }
    val distance = splitted[1][1].trim().split("\\s+".toRegex()).map { it.toInt() }
    println(time)
    println(distance)
    var sum = 1
    for (i in 0 until time.size) {
        println("time: ${time[i]}")
        var counter = 0
        for (j in 1 until time[i].toInt()+1) {
            var distance1 = (time[i].toInt()-j)*j
            if (distance1 > distance[i]) {
                println("distance1: $distance1")
                println("j: $j")
                counter += 1
            }
        }
        sum *= counter
    }
    println(sum)
}