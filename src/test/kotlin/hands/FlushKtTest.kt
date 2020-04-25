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

internal class FlushKtTest {
    @Test
    fun `returns null if no flush exists`() {
        val mockSevenCards = mockk<SevenCards>()
        every { mockSevenCards.cards } returns mockk()

        val mockGetFlushFrom = mockk<GetFlushFrom>()
        every { mockGetFlushFrom(any()) } returns null

        val hand = mockSevenCards.getFlush(mockGetFlushFrom)
        assertThat(hand).isNull()
    }

    @Test
    fun `returns a flush if one exists`() {
        val mockSevenCards = mockk<SevenCards>()
        every { mockSevenCards.cards } returns mockk()

        val mockFlush = listOf(mockk<Card>())
        val mockGetFlushFrom = mockk<GetFlushFrom>()
        every { mockGetFlushFrom(any()) } returns mockFlush

        val hand = mockSevenCards.getFlush(mockGetFlushFrom)!!
        assertThat(hand).isInstanceOf(Flush::class)
        assertThat(hand.cards).isEqualTo(mockFlush)
    }
}