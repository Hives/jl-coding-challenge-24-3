package hands

import Card
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.containsOnly
import assertk.assertions.hasSize
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

internal class FullHouseKtTest {
    @Test
    fun `returns null if no full house exists`() {
        val hand = SevenCards.from("2D 3D 4D 5D 7S 8S 9S").getFullHouse()
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a full house if one exists`() {
        val hand = SevenCards.from("2D 2H 2S 3D 3S JD QD").getFullHouse()!!
        assertThat(hand.pair).containsOnly(
            Card.from("3D"),
            Card.from("3S")
        )
        assertThat(hand.three).containsOnly(
            Card.from("2D"),
            Card.from("2H"),
            Card.from("2S")
        )
    }

    @Test
    fun `returns the best possible full house if there are two three-of-a-kinds`() {
        val hand = SevenCards.from("2D 2H 2S 3D 3H 3S QD").getFullHouse()!!

        assertThat(hand.pair).hasSize(2)

        // TODO this is weird
        hand.pair.forEach {
            assertThat(
                listOf(
                    Card.from("2D"),
                    Card.from("2H"),
                    Card.from("2S")
                )
            ).contains(it)
        }

        assertThat(hand.three).containsOnly(
            Card.from("3D"),
            Card.from("3H"),
            Card.from("3S")
        )
    }

    @Test
    fun `returns the best possible full house if there are two pairs and one three-of-a-kind`() {
        val hand = SevenCards.from("2D 2H 3D 3H 4D 4H 4S").getFullHouse()!!

        assertThat(hand.pair).containsOnly(
            Card.from("3D"),
            Card.from("3H")
        )

        assertThat(hand.three).containsOnly(
            Card.from("4D"),
            Card.from("4H"),
            Card.from("4S")
        )
    }

}