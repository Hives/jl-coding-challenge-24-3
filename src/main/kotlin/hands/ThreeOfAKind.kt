package hands

import Card

fun List<Card>.getThreeOfAKind(): ThreeOfAKind? =
    this.getGroupsOfSize(3).firstOrNull()?.let { ThreeOfAKind(it) }