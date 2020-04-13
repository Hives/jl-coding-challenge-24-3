package hands

import Card

fun List<Card>.getFlush(): List<Card>? = this
    .asSequence()
    .groupBy { it.suit }.map { it.value }
    .filter { it.size >= 5 }
    .map { cards ->
        cards.sortedBy { it.value }.takeLast(5)
    }
    .singleOrNull()