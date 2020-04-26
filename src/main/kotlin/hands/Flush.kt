package hands

import SevenCards

fun createFlushOrNull(getAtLeastFiveCardsOfOneSuit: GetAtLeastFiveCardsOfOneSuit): (SevenCards) -> Flush? {
    return fun(sevenCards: SevenCards): Flush? =
        getAtLeastFiveCardsOfOneSuit(sevenCards)
            ?.sortedByDescending { it.value }
            ?.let { Flush(it.take(5)) }
}
