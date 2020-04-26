package hands

import SevenCards

fun createStraightOrNull(getStraightFrom: GetStraightFrom): (SevenCards) -> Straight? {
    return fun (sevenCards: SevenCards): Straight? =
        getStraightFrom(sevenCards.cards)?.let { Straight(it) }
}