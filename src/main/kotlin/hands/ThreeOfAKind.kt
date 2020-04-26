package hands

import SevenCards

fun threeOfAKindOrNull(sevenCards: SevenCards): ThreeOfAKind? =
    sevenCards.getGroupsOfSize(3).firstOrNull()?.let { ThreeOfAKind(it) }