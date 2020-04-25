package hands

import SevenCards

fun SevenCards.getThreeOfAKind(): ThreeOfAKind? =
    this.getGroupsOfSize(3).firstOrNull()?.let { ThreeOfAKind(it) }