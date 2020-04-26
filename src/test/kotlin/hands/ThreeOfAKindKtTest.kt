package hands

import Card
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

internal class ThreeOfAKindKtTest {
    @Test
    fun `returns null if there is no three of a kind`() {
        val hand = threeOfAKindOrNull(SevenCards.from("2D 3D 4D 5D 6S 7S 7D"))
        assertThat(hand).isNull()
    }

    @Test
    fun `returns three of a kind if there are three of a kind`() {
        val hand = threeOfAKindOrNull(SevenCards.from("2D 3D 4D 5S JS JD JH"))!!
        assertThat(hand.three).containsOnly(
            Card.from("JS"),
            Card.from("JD"),
            Card.from("JH")
        )
    }

    @Test
    fun `returns the best three of a kind if there are two threes of a kinds`() {
        val hand = threeOfAKindOrNull(SevenCards.from("2D 10D 10H 10S JS JD JH"))!!
        assertThat(hand.three).containsOnly(
            Card.from("JS"),
            Card.from("JD"),
            Card.from("JH")
        )
    }
}