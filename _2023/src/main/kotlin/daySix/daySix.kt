package daySix

import java.io.File

fun main() {
    val fileContent = File("_2023/src/main/resources/daySix.txt").readText(Charsets.UTF_8).split("\n")
    val splitted = fileContent.map { it.split(":") }
    val time = splitted[0][1].trim().replace("\\s+".toRegex(), "").toLong()
    val distance = splitted[1][1].trim().replace("\\s+".toRegex(), "").toLong()
    println(time)
    println(distance)


    println("time: ${time}")
    var counter = 0
    for (j in 1 until time.toLong()+1) {
        var distance1 = (time.toLong()-j)*j
        if (distance1 > distance) {
            println("distance1: $distance1")
            println("j: $j")
            counter += 1
        }
    }
    println(counter)

}