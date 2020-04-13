package hands

import Card

fun List<Card>.getSinglePair(): SinglePair? = this
    .groupBy { it.face }
    .map { it.value }
    .filter { it.size == 2 }
    .map {
        SinglePair(it.first().face)
    }
    .firstOrNull()