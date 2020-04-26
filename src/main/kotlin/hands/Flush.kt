package hands

import SevenCards

fun flushOrNull(
    sevenCards: SevenCards,
    getAtLeastFiveCardsOfOneSuit: GetAtLeastFiveCardsOfOneSuit = ::getAtLeastFiveCardsOfOneSuit
): Flush? =
    getAtLeastFiveCardsOfOneSuit(sevenCards)
        ?.sortedByDescending { it.value }
        ?.let { Flush(it.take(5)) }