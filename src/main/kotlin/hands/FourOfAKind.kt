package hands

import SevenCards

fun fourOfAKindOrNull(sevenCards: SevenCards): FourOfAKind? =
    sevenCards.getGroupsOfSize(4).firstOrNull()?.let { FourOfAKind(it) }