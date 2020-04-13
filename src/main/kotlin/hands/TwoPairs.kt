package hands

import Card

fun List<Card>.getTwoPairs(): TwoPairs? = this
    .groupBy { it.face }
    .map { it.value }
    .filter { it.size == 2 }
    .map { it.first().face }
    .sortedByDescending { it.value }
    .let {
        if (it.size < 2) null else TwoPairs(it[0], it[1])
    }