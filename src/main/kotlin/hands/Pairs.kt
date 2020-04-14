package hands

import Card

fun List<Card>.getPairs(): Hand? {
    val pairs = this
        .asSequence()
        .groupBy { it.face }
        .map { it.value }
        .filter { it.size == 2 }
        .sortedByDescending {
            it.first().value
        }

    return when (pairs.size) {
        0 -> null
        1 -> SinglePair(pairs.single())
        else -> TwoPairs(pairs[0], pairs[1])
    }
}