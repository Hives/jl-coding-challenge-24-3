package Hands

import ACE
import CLUBS
import Card
import DIAMONDS
import HEARTS
import KING
import Spots
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class FlushKtTest {
    @Test
    fun `returns null if no flush exists`() {
        assertThat(
            "2D 3D 4D 5D 6S 7S 8S".toCards().getFlush()
        ).isNull()
    }

    @Test
    fun `returns a flush of hearts`() {
        assertThat(
            "2D 3D 4H 5H 6H 7H 8H".toCards().getFlush()!!
        ).containsOnly(
            Card(Spots(4), HEARTS),
            Card(Spots(5), HEARTS),
            Card(Spots(6), HEARTS),
            Card(Spots(7), HEARTS),
            Card(Spots(8), HEARTS)
        )
    }

    @Test
    fun `returns a flush of diamonds`() {
        assertThat(
            "2D 3D 4H 5H 6D 7D 8D".toCards().getFlush()!!
        ).containsOnly(
            Card(Spots(2), DIAMONDS),
            Card(Spots(3), DIAMONDS),
            Card(Spots(6), DIAMONDS),
            Card(Spots(7), DIAMONDS),
            Card(Spots(8), DIAMONDS)
        )
    }

    @Test
    fun `if more than five cards of a suit it returns the best flush`() {
        assertThat(
            "2C 3C 4C 5C 6C KC AC".toCards().getFlush()!!
        ).containsOnly(
            Card(Spots(4), CLUBS),
            Card(Spots(5), CLUBS),
            Card(Spots(6), CLUBS),
            Card(KING, CLUBS),
            Card(ACE, CLUBS)
        )
    }
}