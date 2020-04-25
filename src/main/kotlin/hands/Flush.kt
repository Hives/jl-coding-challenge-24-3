package hands

import SevenCards

fun SevenCards.getFlush(
    getAtLeastFiveCardsOfOneSuit: GetAtLeastFiveCardsOfOneSuit = ::getAtLeastFiveCardsOfOneSuit
): Flush? =
    getAtLeastFiveCardsOfOneSuit(this)
        ?.sortedByDescending { it.value }
        ?.let { Flush(it.take(5)) }