package dayEight

import java.io.File

data class Node(val node: String, val left: String, val right: String)

fun main () {
    val fileContent = File("_2023/src/main/resources/dayEight.txt").readText(Charsets.UTF_8).split("\n")
    val instructions = fileContent[0]

    val nodes = fileContent.drop(2).map { line ->
        val parts = line.split("=")
        val (left, right) = parts[1].trim().removeSurrounding("(", ")").split(",")
        Node(parts[0].trim(), left, right.trim())
    }
    followInstructions(nodes, instructions)


}

fun followInstructions(nodes: List<Node>, instructions: String) {
    var currentNode = nodes.first { it.node == "AAA" }
    var counter = 0
    var index = 0
    println("index max: ${instructions.length}")
    while (currentNode.node != "ZZZ") {
        if(index == instructions.length) {
            index = 0
        }
        currentNode = when (instructions[index]) {
            'L' -> nodes.first { it.node == currentNode.left }
            else -> nodes.first { it.node == currentNode.right }
        }
        index++
        counter++
    }
    println(counter)
    println(currentNode)
}
