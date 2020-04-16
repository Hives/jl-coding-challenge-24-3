package hands

import Card

fun List<Card>.getFourOfAKind(): FourOfAKind? =
    this.getGroupsOfSize(4).firstOrNull()?.let { FourOfAKind(it) }