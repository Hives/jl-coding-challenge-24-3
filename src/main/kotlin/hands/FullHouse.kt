package hands

import Card

fun List<Card>.getFullHouse(): FullHouse? {
    val pairs = this.getGroupsOfSize(2)
    val threes = this.getGroupsOfSize(3)

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