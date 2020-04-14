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

private fun List<Card>.duplicateHighAcesToLowAces() = this
    .flatMap {
        if (it.face == ACE) {
            listOf(it, Card(LOW_ACE, it.suit))
        } else {
            listOf(it)
        }
    }

private fun List<Card>.removeCardsOfDuplicateValue() = this
    .distinctBy { it.value }