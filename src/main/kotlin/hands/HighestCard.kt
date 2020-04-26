package hands

import SevenCards

fun highestCard(sevenCards: SevenCards): HighestCard = sevenCards.cards
    .sortedByDescending { it.value }
    .take(5)
    .let { HighestCard(it) }