import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isFailure
import org.junit.jupiter.api.Test

internal class SevenCardsTest {
    @Test
    fun `can make seven cards from a string`() {
        val sevenCards = SevenCards.from("2D 3C 4S 5H JC QD KS")
        assertThat(sevenCards.cards).containsOnly(
            Card.from("2D"),
            Card.from("3C"),
            Card.from("4S"),
            Card.from("5H"),
            Card.from("JC"),
            Card.from("QD"),
            Card.from("KS")
        )
    }

    @Test
    fun `throws an error if less than 7 cards`() {
        assertThat { SevenCards.from("2D 3C 4S 5H JC QD") }.isFailure()
    }

    @Test
    fun `throws an error if more than 7 cards`() {
        assertThat { SevenCards.from("2D 3C 4S 5H JC QD KS AC") }.isFailure()
    }
}