package hands

import Card
import SevenCards
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNull
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class FlushKtTest {
    @Test
    fun `returns null if can't find five cards of same suit`() {
        val mockSevenCards = mockk<SevenCards>()
        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()
        every { mockGetAtLeastFiveCardsOfOneSuit(any()) } returns null

        val hand = mockSevenCards.getFlush(mockGetAtLeastFiveCardsOfOneSuit)
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a flush if exactly five cards of same suit`() {
        val mockSevenCards = mockk<SevenCards>()
        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()

        val fiveHearts = setOf(
            Card.from("2H"),
            Card.from("3H"),
            Card.from("4H"),
            Card.from("5H"),
            Card.from("6H")
        )
        every { mockGetAtLeastFiveCardsOfOneSuit(any()) } returns fiveHearts

        val hand = mockSevenCards.getFlush(mockGetAtLeastFiveCardsOfOneSuit)!!
        assertThat(hand).isInstanceOf(Flush::class)
        assertThat(hand.cards).containsOnly(
            Card.from("2H"),
            Card.from("3H"),
            Card.from("4H"),
            Card.from("5H"),
            Card.from("6H")
        )
    }

    @Test
    fun `returns the best flush if more than five cards of same suit`() {
        val mockSevenCards = mockk<SevenCards>()
        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()

        val sevenHearts = setOf(
            Card.from("AH"),
            Card.from("2H"),
            Card.from("3H"),
            Card.from("4H"),
            Card.from("5H"),
            Card.from("6H"),
            Card.from("7H")
        )
        every { mockGetAtLeastFiveCardsOfOneSuit(any()) } returns sevenHearts

        val hand = mockSevenCards.getFlush(mockGetAtLeastFiveCardsOfOneSuit)!!
        assertThat(hand.cards).containsOnly(
            Card.from("4H"),
            Card.from("5H"),
            Card.from("6H"),
            Card.from("7H"),
            Card.from("AH")
        )
    }

    @Test
    fun `returns cards in descending order`() {
        val mockSevenCards = mockk<SevenCards>()
        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()

        val fiveHearts = setOf(
            Card.from("2H"),
            Card.from("3H"),
            Card.from("4H"),
            Card.from("5H"),
            Card.from("6H")
        )
        every { mockGetAtLeastFiveCardsOfOneSuit(any()) } returns fiveHearts

        val hand = mockSevenCards.getFlush(mockGetAtLeastFiveCardsOfOneSuit)!!
        assertThat(hand.cards.sortedByDescending { it.value }).isEqualTo(hand.cards)
    }
}