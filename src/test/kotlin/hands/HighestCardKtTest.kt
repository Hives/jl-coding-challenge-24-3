package hands

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

internal class HighestCardKtTest {
    @Test
    fun `Returns a hand of 'highest card' with the five highest cards in descending order`() {
        val sevenCards = SevenCards.from("2D 3C 4S 5H JC QD KS")
        val hand = highestCard(sevenCards)

        assertThat(hand).isInstanceOf(HighestCard::class)
        assertThat(hand.cards).isEqualTo(
            listOf(
                Card.from("KS"),
                Card.from("QD"),
                Card.from("JC"),
                Card.from("5H"),
                Card.from("4S")
            )
        )

    }
}