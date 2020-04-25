package hands

import ACE
import Card
import LOW_ACE
import SevenCards

fun SevenCards.getGroupsOfSize(n: Int) =
    this.cards
        .groupBy { it.face }
        .map { it.value.toSet() }
        .filter { it.size == n }
        .sortedByDescending { it.first().value }

typealias GetStraightFrom = (Collection<Card>) -> List<Card>?
fun getStraightFrom(cards: Collection<Card>): List<Card>? = cards
    .duplicateHighAcesToLowAces()
    .removeCardsOfDuplicateValue()
    .sortedByDescending { it.value }
    .let {
        val first = listOf(it.first())
        val rest = it.drop(1)

        rest.fold(first) { acc, card ->
            when {
                acc.size == 5 -> acc
                card.value == acc.last().value - 1 -> acc + card
                else -> listOf(card)
            }
        }
    }.let { acc ->
        if (acc.size == 5) acc else null
    }

private fun Collection<Card>.duplicateHighAcesToLowAces() =
    this + this.filter { it.face == ACE }.map { Card(LOW_ACE, it.suit) }

private fun Collection<Card>.removeCardsOfDuplicateValue() =
    this.distinctBy { it.value }

typealias GetFlushFrom = (Collection<Card>) -> List<Card>?
fun getFlushFrom(cards: Collection<Card>): List<Card>? =
    cards.asSequence()
        .groupBy { it.suit }
        .map { (suit, cards) -> cards }
        .singleOrNull { it.size >= 5 }
        ?.sortedByDescending { it.value }
        ?.take(5)
