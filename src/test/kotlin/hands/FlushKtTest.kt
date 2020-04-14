package hands

import Card
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class FlushKtTest {
    @Test
    fun `returns null if no flush exists`() {
        val hand = "2D 3D 4D 5D 6S 7S 8S".toCards().getFlush()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a flush of hearts`() {
        val hand = "2D 3D 4H 6H 8H 10H QH".toCards().getFlush()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("4H"),
            Card.fromString("6H"),
            Card.fromString("8H"),
            Card.fromString("10H"),
            Card.fromString("QH")
        )
    }

    @Test
    fun `returns a flush of diamonds`() {
        val hand = "2D 3D 4H 5H 6D 7D 8D".toCards().getFlush()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("2D"),
            Card.fromString("3D"),
            Card.fromString("6D"),
            Card.fromString("7D"),
            Card.fromString("8D")
        )
    }

    @Test
    fun `if more than five cards of a suit it returns the best flush`() {
        val hand = "AC 2C 3C 5C 6C 7C KC".toCards().getFlush()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("5C"),
            Card.fromString("6C"),
            Card.fromString("7C"),
            Card.fromString("KC"),
            Card.fromString("AC")
        )
    }
}