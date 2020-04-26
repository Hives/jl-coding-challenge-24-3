package hands

import SevenCards

fun createStraightFlushOrNull(
    getAtLeastFiveCardsOfOneSuit: GetAtLeastFiveCardsOfOneSuit,
    getStraightFrom: GetStraightFrom
): (SevenCards) -> StraightFlush? {
    return fun(sevenCards: SevenCards): StraightFlush? =
        getAtLeastFiveCardsOfOneSuit(sevenCards)
            ?.let { getStraightFrom(it) }
            ?.let { StraightFlush(it) }
}