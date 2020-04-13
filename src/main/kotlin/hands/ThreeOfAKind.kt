package hands

import Card

fun List<Card>.getThreeOfAKind(): ThreeOfAKind? = this
    .groupBy { it.face }
    .map { it.value }
    .filter { it.size == 3 }
    .map { it.first().face }
    .sortedByDescending { it.value}
    .map {
        ThreeOfAKind(it)
    }
    .firstOrNull()