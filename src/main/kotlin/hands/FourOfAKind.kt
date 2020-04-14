package hands

import Card

fun List<Card>.getFourOfAKind(): FourOfAKind? {
    val fours = this
        .asSequence()
        .groupBy { it.face }
        .map { it.value }
        .filter { it.size == 4 }
        .sortedByDescending {
            it.first().value
        }

    return if (fours.size == 0) {
        null
    } else {
        FourOfAKind(fours.first())
    }

}
