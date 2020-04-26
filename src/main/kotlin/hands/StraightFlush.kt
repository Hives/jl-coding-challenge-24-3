package hands

import SevenCards

fun straightFlushOrNull(
    sevenCards: SevenCards,
    getAtLeastFiveCardsOfOneSuit: GetAtLeastFiveCardsOfOneSuit,
    getStraightFrom: GetStraightFrom
): StraightFlush? =
    getAtLeastFiveCardsOfOneSuit(sevenCards)
        ?.let { getStraightFrom(it) }
        ?.let { StraightFlush(it) }