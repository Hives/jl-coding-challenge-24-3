package hands

import Card

fun List<Card>.getPairs(): Hand? {
    val pairs = this.getGroupsOfSize(2)

    return when (pairs.size) {
        0 -> null
        1 -> Pair(pairs.single())
        else -> TwoPairs(pairs[0], pairs[1])
    }
}