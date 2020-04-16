package hands

import Card

fun List<Card>.getGroupsOfSize(n: Int) =
    this
        .groupBy { it.face }
        .map { it.value.toSet() }
        .filter { it.size == n }
        .sortedByDescending { it.first().value }