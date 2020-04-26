package hands

import SevenCards

fun SevenCards.getHighestCard(): HighestCard = this.cards
    .sortedByDescending { it.value }
    .take(5)
    .let { HighestCard(it) }