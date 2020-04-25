package hands

import SevenCards

fun SevenCards.getStraight(getStraightFrom: GetStraightFrom = ::getStraightFrom): Straight? =
    getStraightFrom(this.cards)?.let { Straight(it) }