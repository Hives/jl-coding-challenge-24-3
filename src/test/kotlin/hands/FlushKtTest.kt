package hands

import Card
import SevenCards
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

internal class FlushKtTest {
    @Test
    fun `returns null if no flush exists`() {
        val hand = SevenCards.from("2D 3D 4D 5D 6S 7S 8S").getFlush()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a flush of hearts`() {
        val hand = SevenCards.from("2D 3D 4H 6H 8H 10H QH").getFlush()!!
        assertThat(hand.cards).containsOnly(
            Card.from("4H"),
            Card.from("6H"),
            Card.from("8H"),
            Card.from("10H"),
            Card.from("QH")
        )
    }

    @Test
    fun `returns a flush of diamonds`() {
        val hand = SevenCards.from("2D 3D 4H 5H 6D 7D 8D").getFlush()!!
        assertThat(hand.cards).containsOnly(
            Card.from("2D"),
            Card.from("3D"),
            Card.from("6D"),
            Card.from("7D"),
            Card.from("8D")
        )
    }

    @Test
    fun `if more than five cards of a suit it returns the best flush`() {
        val hand = SevenCards.from("AC 2C 3C 5C 6C 7C KC").getFlush()!!
        assertThat(hand.cards).containsOnly(
            Card.from("5C"),
            Card.from("6C"),
            Card.from("7C"),
            Card.from("KC"),
            Card.from("AC")
        )
    }
}