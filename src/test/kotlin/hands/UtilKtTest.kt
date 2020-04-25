package hands

import Card
import HEARTS
import LOW_ACE
import SevenCards
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class UtilKtTest {

    @Nested
    inner class GettingDuplicates {
        @Test
        fun `can get pairs from hand`() {
            val pairs = SevenCards.from("AS JD AD 10S 2H 2D 3C").getGroupsOfSize(2)
            assertThat(pairs).containsOnly(
                setOf(Card.from("AS"), Card.from("AD")),
                setOf(Card.from("2H"), Card.from("2D"))
            )
        }

        @Test
        fun `can get threes from hand`() {
            val pairs = SevenCards.from("AS AC AD JS 2H 2D 2C").getGroupsOfSize(3)
            assertThat(pairs).containsOnly(
                setOf(Card.from("AS"), Card.from("AD"), Card.from("AC")),
                setOf(Card.from("2H"), Card.from("2D"), Card.from("2C"))
            )
        }

        @Test
        fun `returns groups in descending order of value`() {
            val pairs = SevenCards.from("2C 2D 7C 7D 3C 3D AC").getGroupsOfSize(2)
            assertThat(pairs).containsExactly(
                setOf(Card.from("7C"), Card.from("7D")),
                setOf(Card.from("3C"), Card.from("3D")),
                setOf(Card.from("2C"), Card.from("2D"))
            )
        }
    }

    @Nested
    inner class GettingStraights {
        @Test
        fun `returns null if no straight exists`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("2D"),
                    Card.from("3D"),
                    Card.from("4D"),
                    Card.from("5D"),
                    Card.from("7S"),
                    Card.from("8S"),
                    Card.from("9S")
                )
            )
            assertThat(hand).isNull()
        }

        @Test
        fun `returns a straight when the straight is the highest five cards`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("2D"),
                    Card.from("3C"),
                    Card.from("5H"),
                    Card.from("6S"),
                    Card.from("7D"),
                    Card.from("8C"),
                    Card.from("9H")
                )
            )!!

            assertThat(hand).containsOnly(
                Card.from("5H"),
                Card.from("6S"),
                Card.from("7D"),
                Card.from("8C"),
                Card.from("9H")
            )
        }

        @Test
        fun `returns a straight when the straight is the lowest five cards`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("5H"),
                    Card.from("6S"),
                    Card.from("7D"),
                    Card.from("8C"),
                    Card.from("9H"),
                    Card.from("JD"),
                    Card.from("QS")
                )
            )!!

            assertThat(hand).containsOnly(
                Card.from("5H"),
                Card.from("6S"),
                Card.from("7D"),
                Card.from("8C"),
                Card.from("9H")
            )
        }

        @Test
        fun `returns a straight when the straight is the middle five cards`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("2D"),
                    Card.from("5H"),
                    Card.from("6S"),
                    Card.from("7D"),
                    Card.from("8C"),
                    Card.from("9H"),
                    Card.from("QS")
                )
            )!!

            assertThat(hand).containsOnly(
                Card.from("5H"),
                Card.from("6S"),
                Card.from("7D"),
                Card.from("8C"),
                Card.from("9H")
            )
        }

        @Test
        fun `returns the best straight when there are more than five in a row`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("3D"),
                    Card.from("4S"),
                    Card.from("5H"),
                    Card.from("6S"),
                    Card.from("7D"),
                    Card.from("8C"),
                    Card.from("9H")
                )
            )!!

            assertThat(hand).containsOnly(
                Card.from("5H"),
                Card.from("6S"),
                Card.from("7D"),
                Card.from("8C"),
                Card.from("9H")
            )
        }

        @Test
        fun `can get a straight up to high ace`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("3D"),
                    Card.from("4S"),
                    Card.from("10H"),
                    Card.from("JS"),
                    Card.from("QD"),
                    Card.from("KC"),
                    Card.from("AH")
                )
            )!!

            assertThat(hand).containsOnly(
                Card.from("10H"),
                Card.from("JS"),
                Card.from("QD"),
                Card.from("KC"),
                Card.from("AH")
            )
        }

        @Test
        fun `can get a straight up to a 5, with a low ace`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("AH"),
                    Card.from("2S"),
                    Card.from("3D"),
                    Card.from("4C"),
                    Card.from("5H"),
                    Card.from("7D"),
                    Card.from("8C")
                )
            )!!

            assertThat(hand).containsOnly(
                Card(LOW_ACE, HEARTS),
                Card.from("2S"),
                Card.from("3D"),
                Card.from("4C"),
                Card.from("5H")
            )
        }

        @Test
        fun `can get a straight when there are multiple cards of the same value`() {
            val hand = getStraightFrom(
                setOf(
                    Card.from("5H"),
                    Card.from("6S"),
                    Card.from("7D"),
                    Card.from("8C"),
                    Card.from("8S"),
                    Card.from("8H"),
                    Card.from("9H")
                )
            )!!

            assertThat(hand).containsOnly(
                Card.from("5H"),
                Card.from("6S"),
                Card.from("7D"),
                Card.from("8C"), // TODO: we don't actually care which 8 this is
                Card.from("9H")
            )
        }
    }

    @Nested
    inner class GettingFlushes {
        @Test
        fun `returns null if no flush exists`() {
            val hand = getFlushFrom(
                setOf(
                    Card.from("2D"),
                    Card.from("3D"),
                    Card.from("4D"),
                    Card.from("5D"),
                    Card.from("6S"),
                    Card.from("7S"),
                    Card.from("8S")
                )
            )
            assertThat(hand).isNull()
        }

        @Test
        fun `returns a flush of hearts`() {
            val hand = getFlushFrom(setOf(
                Card.from("2D"),
                Card.from("3D"),
                Card.from("4H"),
                Card.from("6H"),
                Card.from("8H"),
                Card.from("10H"),
                Card.from("QH")
            ))!!

            assertThat(hand).containsOnly(
                Card.from("4H"),
                Card.from("6H"),
                Card.from("8H"),
                Card.from("10H"),
                Card.from("QH")
            )
        }

        @Test
        fun `returns a flush of diamonds`() {
            val hand = getFlushFrom(setOf(
                Card.from("2D"),
                Card.from("3D"),
                Card.from("4H"),
                Card.from("5H"),
                Card.from("6D"),
                Card.from("7D"),
                Card.from("8D")
            ))!!

            assertThat(hand).containsOnly(
                Card.from("2D"),
                Card.from("3D"),
                Card.from("6D"),
                Card.from("7D"),
                Card.from("8D")
            )
        }

        @Test
        fun `if more than five cards of a suit it returns the best flush`() {
            val hand = getFlushFrom(setOf(
                Card.from("AC"),
                Card.from("2C"),
                Card.from("3C"),
                Card.from("5C"),
                Card.from("6C"),
                Card.from("7C"),
                Card.from("KC")
            ))!!

            assertThat(hand).containsOnly(
                Card.from("5C"),
                Card.from("6C"),
                Card.from("7C"),
                Card.from("KC"),
                Card.from("AC")
            )
        }
    }
}