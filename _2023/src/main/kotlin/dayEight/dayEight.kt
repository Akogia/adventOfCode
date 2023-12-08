package dayEight

import java.io.File

data class Node(var node: String, val left: String, val right: String)

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
    var currentNodes = nodes.filter { it.node.endsWith('A') }.toMutableList()
    var counter = 0.toLong()
    var index = 0
    //println("index max: ${instructions.length}")
    // check if for every current node the last letter is Z
    val nodesMap = mutableMapOf<Node, Long>()
    while (!currentNodes.all { it.node.endsWith('Z') }) {
        val node : Node

        if(index == instructions.length) {
            index = 0
        }
        println("currentNodes: $currentNodes")
        val listNodes = currentNodes.map { currentNode ->
            when (instructions[index]) {
                'L' ->  nodes.first { it.node == currentNode.left }
                else -> nodes.first { it.node == currentNode.right }
        }}.toMutableList()
        if (listNodes.any { it.node.endsWith('Z') }) {
            node = listNodes.first { it.node.endsWith('Z') }
            nodesMap.put(node, counter + 1)
            listNodes.remove(node)
        }
        index++
        counter++
        currentNodes = listNodes.toMutableList()

        println("counter: $counter")
    }
    println("nodesMap: $nodesMap")
    println(counter)
    println(currentNodes)
}
