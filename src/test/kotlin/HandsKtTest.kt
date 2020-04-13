import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class HandsKtTest {

    @Nested
    @DisplayName("get flush")
    inner class Flush {

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
                Card(Number(4), HEARTS),
                Card(Number(5), HEARTS),
                Card(Number(6), HEARTS),
                Card(Number(7), HEARTS),
                Card(Number(8), HEARTS)
            )
        }

        @Test
        fun `returns a flush of diamonds`() {
            assertThat(
                "2D 3D 4H 5H 6D 7D 8D".toCards().getFlush()!!
            ).containsOnly(
                Card(Number(2), DIAMONDS),
                Card(Number(3), DIAMONDS),
                Card(Number(6), DIAMONDS),
                Card(Number(7), DIAMONDS),
                Card(Number(8), DIAMONDS)
            )
        }

        @Test
        fun `if more than five cards of a suit it returns the best flush`() {
            assertThat(
                "2C 3C 4C 5C 6C KC AC".toCards().getFlush()!!
            ).containsOnly(
                Card(Number(4), CLUBS),
                Card(Number(5), CLUBS),
                Card(Number(6), CLUBS),
                Card(KING, CLUBS),
                Card(ACE, CLUBS)
            )
        }
    }

    @Nested
    @DisplayName("get straight")
    inner class Straight {

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
                Card(Number(5), HEARTS),
                Card(Number(6), SPADES),
                Card(Number(7), DIAMONDS),
                Card(Number(8), CLUBS),
                Card(Number(9), HEARTS)
            )
        }

        @Test
        fun `returns a straight when the straight is the lowest five cards`() {
            assertThat(
                "5H 6S 7D 8C 9H JD QS".toCards().getStraight()!!
            ).containsOnly(
                Card(Number(5), HEARTS),
                Card(Number(6), SPADES),
                Card(Number(7), DIAMONDS),
                Card(Number(8), CLUBS),
                Card(Number(9), HEARTS)
            )
        }

        @Test
        fun `returns a straight when the straight is the middle five cards`() {
            assertThat(
                "2D 5H 6S 7D 8C 9H QS".toCards().getStraight()!!
            ).containsOnly(
                Card(Number(5), HEARTS),
                Card(Number(6), SPADES),
                Card(Number(7), DIAMONDS),
                Card(Number(8), CLUBS),
                Card(Number(9), HEARTS)
            )
        }

        @Test
        fun `returns the best straight when there are more than five in a row`() {
            assertThat(
                "3D 4S 5H 6S 7D 8C 9H".toCards().getStraight()!!
            ).containsOnly(
                Card(Number(5), HEARTS),
                Card(Number(6), SPADES),
                Card(Number(7), DIAMONDS),
                Card(Number(8), CLUBS),
                Card(Number(9), HEARTS)
            )
        }

        @Test
        fun `can get a straight up to high ace`() {
            assertThat(
                "3D 4S 10H JS QD KC AH".toCards().getStraight()!!
            ).containsOnly(
                Card(Number(10), HEARTS),
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
                Card(Number(2), SPADES),
                Card(Number(3), DIAMONDS),
                Card(Number(4), CLUBS),
                Card(Number(5), HEARTS)
            )
        }

        @Test
        fun `can get a straight when there are multiple cards of the same value`() {
            assertThat(
                "5H 6S 7D 8C 8S 8H 9H".toCards().getStraight()!!
            ).containsOnly(
                Card(Number(5), HEARTS),
                Card(Number(6), SPADES),
                Card(Number(7), DIAMONDS),
                Card(Number(8), CLUBS),
                Card(Number(9), HEARTS)
            )
        }
    }
}