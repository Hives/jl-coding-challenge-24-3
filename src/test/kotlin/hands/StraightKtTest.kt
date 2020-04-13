package hands

import ACE
import CLUBS
import Card
import DIAMONDS
import HEARTS
import JACK
import KING
import LOW_ACE
import QUEEN
import SPADES
import Spots
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class StraightKtTest {

    @Test
    fun `returns null if no straight exists`() {
        assertThat(
            "2D 3D 4D 5D 7S 8S 9S".toCards().getStraight()
        ).isNull()
    }

    @Test
    fun `returns a straight when the straight is the highest five cards`() {
        assertThat(
            "2D 3C 5H 6S 7D 8C 9H".toCards().getStraight()!!
        ).containsOnly(
            Card(Spots(5), HEARTS),
            Card(Spots(6), SPADES),
            Card(Spots(7), DIAMONDS),
            Card(Spots(8), CLUBS),
            Card(Spots(9), HEARTS)
        )
    }

    @Test
    fun `returns a straight when the straight is the lowest five cards`() {
        assertThat(
            "5H 6S 7D 8C 9H JD QS".toCards().getStraight()!!
        ).containsOnly(
            Card(Spots(5), HEARTS),
            Card(Spots(6), SPADES),
            Card(Spots(7), DIAMONDS),
            Card(Spots(8), CLUBS),
            Card(Spots(9), HEARTS)
        )
    }

    @Test
    fun `returns a straight when the straight is the middle five cards`() {
        assertThat(
            "2D 5H 6S 7D 8C 9H QS".toCards().getStraight()!!
        ).containsOnly(
            Card(Spots(5), HEARTS),
            Card(Spots(6), SPADES),
            Card(Spots(7), DIAMONDS),
            Card(Spots(8), CLUBS),
            Card(Spots(9), HEARTS)
        )
    }

    @Test
    fun `returns the best straight when there are more than five in a row`() {
        assertThat(
            "3D 4S 5H 6S 7D 8C 9H".toCards().getStraight()!!
        ).containsOnly(
            Card(Spots(5), HEARTS),
            Card(Spots(6), SPADES),
            Card(Spots(7), DIAMONDS),
            Card(Spots(8), CLUBS),
            Card(Spots(9), HEARTS)
        )
    }

    @Test
    fun `can get a straight up to high ace`() {
        assertThat(
            "3D 4S 10H JS QD KC AH".toCards().getStraight()!!
        ).containsOnly(
            Card(Spots(10), HEARTS),
            Card(JACK, SPADES),
            Card(QUEEN, DIAMONDS),
            Card(KING, CLUBS),
            Card(ACE, HEARTS)
        )
    }

    @Test
    fun `can get a straight up to a 5, with a low ace`() {
        assertThat(
            "AH 2S 3D 4C 5H 7D 8S".toCards().getStraight()!!
        ).containsOnly(
            Card(LOW_ACE, HEARTS),
            Card(Spots(2), SPADES),
            Card(Spots(3), DIAMONDS),
            Card(Spots(4), CLUBS),
            Card(Spots(5), HEARTS)
        )
    }

    @Test
    fun `can get a straight when there are multiple cards of the same value`() {
        assertThat(
            "5H 6S 7D 8C 8S 8H 9H".toCards().getStraight()!!
        ).containsOnly(
            Card(Spots(5), HEARTS),
            Card(Spots(6), SPADES),
            Card(Spots(7), DIAMONDS),
            Card(Spots(8), CLUBS),
            Card(Spots(9), HEARTS)
        )
    }

}