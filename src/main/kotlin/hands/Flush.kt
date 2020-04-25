package hands

import SevenCards

fun SevenCards.getFlush(): Flush? = this.cards
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