package hands

import SevenCards

fun SevenCards.getFlush(): Flush? = this.cards
    .asSequence()
    .groupBy { it.suit }
    .map { (suit, cards) ->
        cards
    }.singleOrNull { it.size >= 5 }
    ?.let { cards ->
        Flush(
            cards.sortedByDescending { it.value }.take(5)
        )
    }