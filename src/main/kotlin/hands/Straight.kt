package hands

import ACE
import Card
import LOW_ACE
import SevenCards

fun SevenCards.getStraight(): Straight? = this.cards
    .duplicateHighAcesToLowAces()
    .removeCardsOfDuplicateValue()
    .sortedByDescending { it.value }
    .let {
        it.drop(1).fold(listOf(it.first())) { acc, card ->
            when {
                acc.size == 5 -> acc
                card.value == acc.last().value - 1 -> acc + card
                else -> listOf(card)
            }
        }
    }.let { acc ->
        if (acc.size >= 5) Straight(acc) else null
    }

private fun Collection<Card>.duplicateHighAcesToLowAces() =
    this + this.filter { it.face == ACE }.map { Card(LOW_ACE, it.suit) }

private fun Collection<Card>.removeCardsOfDuplicateValue() =
    this.distinctBy { it.value }