package hands

import Card

fun List<Card>.getFlush(): Flush? = this
    .asSequence()
    .groupBy { it.suit }
    .map { it.value }
    .filter { it.size >= 5 }
    .singleOrNull()
    ?.let { cards ->
        Flush(
            cards.sortedByDescending { it.value }.take(5)
        )
    }