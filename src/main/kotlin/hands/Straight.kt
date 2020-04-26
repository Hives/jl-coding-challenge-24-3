package hands

import SevenCards

fun straightOrNull(sevenCards: SevenCards, getStraightFrom: GetStraightFrom = ::getStraightFrom): Straight? =
    getStraightFrom(sevenCards.cards)?.let { Straight(it) }