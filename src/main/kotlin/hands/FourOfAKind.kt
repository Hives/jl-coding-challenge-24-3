package hands

import SevenCards

fun SevenCards.getFourOfAKind(): FourOfAKind? =
    this.getGroupsOfSize(4).firstOrNull()?.let { FourOfAKind(it) }