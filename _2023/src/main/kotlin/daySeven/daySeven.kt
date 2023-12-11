package daySeven

import java.io.File

data class Hand(val cards: String, val value: Int)

val cardStrength = mapOf('A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10, '9' to 9, '8' to 8, '7' to 7, '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2)
val sortedHands = mutableListOf<List<String>>()
fun main() {
    val fileContent = File("_2023/src/main/resources/test.txt").readText(Charsets.UTF_8).split("\n")
    val splitted = fileContent.map { line ->
        val parts = line.split(" ")
        val cards = parts[0]
        val value = parts[1].toInt()
        Hand(cards, value)
    }
    val orderedHands = splitted.sortedWith { firstHand, secondHand ->
        compareHands(firstHand, secondHand)}
    var totalWinnings = 0
    for ((index, hand) in orderedHands.withIndex()) {
        totalWinnings += hand.value * (index + 1)
    }
    println("totalWinnings = $totalWinnings")

    println(orderedHands)
}

fun compareHands(firstHand: Hand, secondHand: Hand) : Int {
    val typePriority = mapOf(
            "AAAAA" to 6,
            "AA8AA" to 5,
            "23332" to 4,
            "TTT98" to 3,
            "23432" to 2,
            "A23A4" to 1,
            "23456" to 0
    )
    val type1 = typePriority.entries.find { firstHand.cards.matches(Regex(it.key)) }?.value ?: -1
    val type2 = typePriority.entries.find { secondHand.cards.matches(Regex(it.key)) }?.value ?: -1

    val typeComparison = type2 - type1
    if (typeComparison != 0) {
        return typeComparison
    }

    // If hands have the same type, compare based on cards
    for (i in 0 until 5) {
        val card1 = firstHand.cards[i]
        val card2 = secondHand.cards[i]

        if (card1 != card2) {
            return card2.compareTo(card1)
        }
    }
    // If all cards are equal, hands are considered equal in strength
    return 0
}