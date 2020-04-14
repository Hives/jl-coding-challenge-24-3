package hands

import Card
import HEARTS
import LOW_ACE
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class StraightKtTest {

    @Test
    fun `returns null if no straight exists`() {
        val hand = "2D 3D 4D 5D 7S 8S 9S".toCards().getStraight()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a straight when the straight is the highest five cards`() {
        val hand = "2D 3C 5H 6S 7D 8C 9H".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("5H"),
            Card.fromString("6S"),
            Card.fromString("7D"),
            Card.fromString("8C"),
            Card.fromString("9H")
        )
    }

    @Test
    fun `returns a straight when the straight is the lowest five cards`() {
        val hand = "5H 6S 7D 8C 9H JD QS".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("5H"),
            Card.fromString("6S"),
            Card.fromString("7D"),
            Card.fromString("8C"),
            Card.fromString("9H")
        )
    }

    @Test
    fun `returns a straight when the straight is the middle five cards`() {
        val hand = "2D 5H 6S 7D 8C 9H QS".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("5H"),
            Card.fromString("6S"),
            Card.fromString("7D"),
            Card.fromString("8C"),
            Card.fromString("9H")
        )
    }

    @Test
    fun `returns the best straight when there are more than five in a row`() {
        val hand = "3D 4S 5H 6S 7D 8C 9H".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("5H"),
            Card.fromString("6S"),
            Card.fromString("7D"),
            Card.fromString("8C"),
            Card.fromString("9H")
        )
    }

    @Test
    fun `can get a straight up to high ace`() {
        val hand = "3D 4S 10H JS QD KC AH".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("10H"),
            Card.fromString("JS"),
            Card.fromString("QD"),
            Card.fromString("KC"),
            Card.fromString("AH")
        )
    }

    @Test
    fun `can get a straight up to a 5, with a low ace`() {
        val hand = "AH 2S 3D 4C 5H 7D 8S".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card(LOW_ACE, HEARTS),
            Card.fromString("2S"),
            Card.fromString("3D"),
            Card.fromString("4C"),
            Card.fromString("5H")
        )
    }

    @Test
    fun `can get a straight when there are multiple cards of the same value`() {
        val hand = "5H 6S 7D 8C 8S 8H 9H".toCards().getStraight()!!
        assertThat(hand.cards).containsOnly(
            Card.fromString("5H"),
            Card.fromString("6S"),
            Card.fromString("7D"),
            Card.fromString("8C"), // TODO: we don't actually care which 8 this is
            Card.fromString("9H")
        )
    }
}