package hands

import Card
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class ThreeOfAKindKtTest {
    @Test
    fun `returns null if there is no three of a kind`() {
        val hand = "2D 3D 4D 5D 6S 7S 7S".toCards().getThreeOfAKind()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns three of a kind if there are three of a kind`() {
        val hand = "2D 3D 4D 5S JS JD JH".toCards().getThreeOfAKind()!!
        assertThat(hand.three).containsOnly(
            Card.fromString("JS"),
            Card.fromString("JD"),
            Card.fromString("JH")
        )
    }

    @Test
    fun `returns the best three of a kind if there are two threes of a kinds`() {
        val hand = "2D 10D 10H 10S JS JD JH".toCards().getThreeOfAKind()!!
        assertThat(hand.three).containsOnly(
            Card.fromString("JS"),
            Card.fromString("JD"),
            Card.fromString("JH")
        )
    }
}