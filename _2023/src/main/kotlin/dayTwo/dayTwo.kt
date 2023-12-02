package dayTwo

import dayOne.sum
import java.io.File

fun main() {
    // Game 100: 11 red, 1 blue, 2 green; 3 red, 3 green; 1 blue, 8 red, 4 green; 5 green, 5 blue, 1 red; 2 green, 1 red, 6 blue; 2 green, 8 red, 1 blue
    // 12 red, 13 green and 14 blue
    val fileContent = File("_2023/src/main/resources/dayTwo.txt").readText(Charsets.UTF_8).split("\n")
    val splitted = fileContent.map { it.split(":") }
    var count = 0
    for (element in splitted) {
        val gameNumber = element[0].split(" ")[1].toInt()
        val games = element[1].split(";")
        val gamePairs = games.map { it.split(",") }
        if (checkGame(gamePairs)) {
            println("Game $gameNumber is valid")
            count += gameNumber
        } else {
            println("Game $gameNumber is invalid")
        }
    }
    println("Sum of valid games: $count")

}

fun checkGame(game: List<List<String>>): Boolean {
    for(element in game) {
        for(cube in element) {
            val splitted = cube.split(" ")
            val color = splitted[2]
            val number = splitted[1].toInt()
            if(color == "red" && number > 12) {
                return false
                break
            }
            if(color == "green" && number > 13) {
                return false
                break
            }
            if(color == "blue" && number > 14) {
                return false
                break
            }
        }

    }
    return true
}