package hands

import Card
import SevenCards
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNull
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class StraightFlushKtTest {
    @Test
    fun `returns null if can't find five cards of the same suit`() {
        val mockSevenCards = mockk<SevenCards>()

        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()
        every { mockGetAtLeastFiveCardsOfOneSuit(mockSevenCards) } returns null

        val hand = mockSevenCards.getStraightFlush(mockGetAtLeastFiveCardsOfOneSuit, mockk())
        assertThat(hand).isNull()
    }

    @Test
    fun `returns null if at least five cards of the same suit, but they don't contain a straight`() {
        val mockSevenCards = mockk<SevenCards>()

        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()
        val mockCardsOfSameSuit = mockk<Set<Card>>()
        every { mockGetAtLeastFiveCardsOfOneSuit(mockSevenCards) } returns mockCardsOfSameSuit

        val mockGetStraightFrom = mockk<GetStraightFrom>()
        every { mockGetStraightFrom(mockCardsOfSameSuit) } returns null

        val hand = mockSevenCards.getStraightFlush(mockGetAtLeastFiveCardsOfOneSuit, mockGetStraightFrom)
        assertThat(hand).isNull()
    }

    @Test
    fun `returns straight flush if there are five cards of the same suit which make up a straight`() {
        val mockSevenCards = mockk<SevenCards>()

        val mockGetAtLeastFiveCardsOfOneSuit = mockk<GetAtLeastFiveCardsOfOneSuit>()
        val mockCardsOfSameSuit = mockk<Set<Card>>()
        every { mockGetAtLeastFiveCardsOfOneSuit(mockSevenCards) } returns mockCardsOfSameSuit

        val mockGetStraightFrom = mockk<GetStraightFrom>()
        val mockStraight = mockk<List<Card>>()
        every { mockGetStraightFrom(mockCardsOfSameSuit) } returns mockStraight

        val hand = mockSevenCards.getStraightFlush(mockGetAtLeastFiveCardsOfOneSuit, mockGetStraightFrom)!!
        assertThat(hand).isInstanceOf(StraightFlush::class)
        assertThat(hand.cards).isEqualTo(mockStraight)
    }
}