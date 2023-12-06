package dayFour

import java.io.File

val copiedElement = mutableListOf<Pair<Int,List<String>>>()
var splitted = mutableListOf<List<String>>()
data class Card(val cardID: Int, val winningNumber: List<Int>, val chosenNumber: List<Int>, var scratched: Boolean = false)

fun main() {
    val fileContent = File("_2023/src/main/resources/dayFour.txt").readText(Charsets.UTF_8).split("\n")
    val cards = fileContent.map { line ->
        val parts = line.split(": ", " | ")
        val cardID = parts[0].split("\\s+".toRegex())[1].toInt()
        val winningNumber = parts[1].trim().split("\\s+".toRegex()).map { it.toInt() }
        val chosenNumber = parts[2].trim().split("\\s+".toRegex()).map { it.toInt() }
        Card(cardID, winningNumber, chosenNumber)
    }.associateBy { it.cardID }.toMutableMap()
    scratchCards(cards)
    println(cards.size)
}

fun scratchCards(cards: MutableMap<Int, Card>) {
    var cardID = 1

    while (cardID <= cards.size) {
        val card = cards[cardID]
        println("card: $card")
        if (card != null && !card.scratched) {
            val commonNumbers = card.winningNumber.intersect(card.chosenNumber)
            println("commonNumbers: $commonNumbers")
            for (i in 1..commonNumbers.size) {

                val copy = cards[card.cardID + i]!!.copy()
                copy.scratched = false
                cards[cards.size + 1] = copy
                println("copy: $copy")
            }
            card.scratched = true
        }
        cardID++
    }
}