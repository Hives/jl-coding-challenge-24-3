package hands

import SevenCards

fun pairsOrNull(sevenCards: SevenCards): Hand? {
    val pairs = sevenCards.getGroupsOfSize(2)

    return when (pairs.size) {
        0 -> null
        1 -> SinglePair(pairs.single())
        else -> TwoPairs(pairs[0], pairs[1])
    }
}