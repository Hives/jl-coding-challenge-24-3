package hands

import Card
import HEARTS
import LOW_ACE
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

internal class StraightKtTest {

    @Test
    fun `returns null if no straight exists`() {
        val hand = SevenCards.from("2D 3D 4D 5D 7S 8S 9S").getStraight()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a straight when the straight is the highest five cards`() {
        val hand = SevenCards.from("2D 3C 5H 6S 7D 8C 9H").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.from("5H"),
            Card.from("6S"),
            Card.from("7D"),
            Card.from("8C"),
            Card.from("9H")
        )
    }

    @Test
    fun `returns a straight when the straight is the lowest five cards`() {
        val hand = SevenCards.from("5H 6S 7D 8C 9H JD QS").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.from("5H"),
            Card.from("6S"),
            Card.from("7D"),
            Card.from("8C"),
            Card.from("9H")
        )
    }

    @Test
    fun `returns a straight when the straight is the middle five cards`() {
        val hand = SevenCards.from("2D 5H 6S 7D 8C 9H QS").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.from("5H"),
            Card.from("6S"),
            Card.from("7D"),
            Card.from("8C"),
            Card.from("9H")
        )
    }

    @Test
    fun `returns the best straight when there are more than five in a row`() {
        val hand = SevenCards.from("3D 4S 5H 6S 7D 8C 9H").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.from("5H"),
            Card.from("6S"),
            Card.from("7D"),
            Card.from("8C"),
            Card.from("9H")
        )
    }

    @Test
    fun `can get a straight up to high ace`() {
        val hand = SevenCards.from("3D 4S 10H JS QD KC AH").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.from("10H"),
            Card.from("JS"),
            Card.from("QD"),
            Card.from("KC"),
            Card.from("AH")
        )
    }

    @Test
    fun `can get a straight up to a 5, with a low ace`() {
        val hand = SevenCards.from("AH 2S 3D 4C 5H 7D 8S").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card(LOW_ACE, HEARTS),
            Card.from("2S"),
            Card.from("3D"),
            Card.from("4C"),
            Card.from("5H")
        )
    }

    @Test
    fun `can get a straight when there are multiple cards of the same value`() {
        val hand = SevenCards.from("5H 6S 7D 8C 8S 8H 9H").getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.from("5H"),
            Card.from("6S"),
            Card.from("7D"),
            Card.from("8C"), // TODO: we don't actually care which 8 this is
            Card.from("9H")
        )
    }
}