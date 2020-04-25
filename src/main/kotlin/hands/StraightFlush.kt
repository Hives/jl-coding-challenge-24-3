package hands

import SevenCards

fun SevenCards.getStraightFlush(
    getAtLeastFiveCardsOfOneSuit: GetAtLeastFiveCardsOfOneSuit,
    getStraightFrom: GetStraightFrom
): StraightFlush? =
    getAtLeastFiveCardsOfOneSuit(this)
        ?.let { getStraightFrom(it) }
        ?.let { StraightFlush(it) }