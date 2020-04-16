package hands

import ACE
import Card
import LOW_ACE

fun List<Card>.getStraight(): Straight? = this
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

private fun List<Card>.duplicateHighAcesToLowAces() =
    this + this.filter { it.face == ACE }.map { Card(LOW_ACE, it.suit) }

private fun List<Card>.removeCardsOfDuplicateValue() =
    this.distinctBy { it.value }