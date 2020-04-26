package hands

import SevenCards

fun fullHouseOrNull(sevenCards: SevenCards): FullHouse? {
    val pairs = sevenCards.getGroupsOfSize(2)
    val threes = sevenCards.getGroupsOfSize(3)

    return when {
        threes.size == 1 && pairs.size > 0 -> FullHouse(
            three = threes.single(),
            pair = pairs.first()
        )
        threes.size > 1 -> FullHouse(
            three = threes[0],
            pair = threes[1].take(2).toSet()
        )
        else -> null
    }
}