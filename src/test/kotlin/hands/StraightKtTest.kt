package hands

import ACE
import Spots
import assertk.assertThat
import assertk.assertions.isEqualTo
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
        ).isEqualTo(Straight(Spots(9)))
    }

    @Test
    fun `returns a straight when the straight is the lowest five cards`() {
        assertThat(
            "5H 6S 7D 8C 9H JD QS".toCards().getStraight()!!
        ).isEqualTo(Straight(Spots(9)))
    }

    @Test
    fun `returns a straight when the straight is the middle five cards`() {
        assertThat(
            "2D 5H 6S 7D 8C 9H QS".toCards().getStraight()!!
        ).isEqualTo(Straight(Spots(9)))
    }

    @Test
    fun `returns the best straight when there are more than five in a row`() {
        assertThat(
            "3D 4S 5H 6S 7D 8C 9H".toCards().getStraight()!!
        ).isEqualTo(Straight(Spots(9)))
    }

    @Test
    fun `can get a straight up to high ace`() {
        assertThat(
            "3D 4S 10H JS QD KC AH".toCards().getStraight()!!
        ).isEqualTo(Straight(ACE))
    }

    @Test
    fun `can get a straight up to a 5, with a low ace`() {
        assertThat(
            "AH 2S 3D 4C 5H 7D 8S".toCards().getStraight()!!
        ).isEqualTo(Straight(Spots(5)))
    }

    @Test
    fun `can get a straight when there are multiple cards of the same value`() {
        assertThat(
            "5H 6S 7D 8C 8S 8H 9H".toCards().getStraight()!!
        ).isEqualTo(Straight(Spots(9)))

    }

}