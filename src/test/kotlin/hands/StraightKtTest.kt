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

internal class StraightKtTest {

    @Test
    fun `returns null if no straight exists`() {
        val mockSevenCards = mockk<SevenCards>()
        every { mockSevenCards.cards } returns mockk()

        val mockGetStraightFrom = mockk<GetStraightFrom>()
        every { mockGetStraightFrom(any()) } returns null

        val hand = straightOrNull(mockSevenCards, mockGetStraightFrom)
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a straight if one exists`() {
        val mockSevenCards = mockk<SevenCards>()
        every { mockSevenCards.cards } returns mockk()

        val mockStraight = listOf(mockk<Card>())
        val mockGetStraightFrom = mockk<GetStraightFrom>()
        every { mockGetStraightFrom(any()) } returns mockStraight

        val hand = straightOrNull(mockSevenCards, mockGetStraightFrom)!!
        assertThat(hand).isInstanceOf(Straight::class)
        assertThat(hand.cards).isEqualTo(mockStraight)
    }
}