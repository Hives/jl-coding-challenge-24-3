package hands

import SevenCards

fun SevenCards.getGroupsOfSize(n: Int) =
    this.cards
        .groupBy { it.face }
        .map { it.value.toSet() }
        .filter { it.size == n }
        .sortedByDescending { it.first().value }