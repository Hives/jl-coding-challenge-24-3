import assertk.assertThat
import assertk.assertions.isEqualTo
import hands.Hand
import hands.HighestCard
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test

internal class GetBestHandKtTest {
    @Test
    fun `call the hand detectors in order and return the first hand that gets returned`() {
        val mockSevenCards = mockk<SevenCards>()

        val failingHandDetector1 = mockk<HandDetector>()
        every { failingHandDetector1(any()) } returns null

        val failingHandDetector2 = mockk<HandDetector>()
        every { failingHandDetector2(any()) } returns null

        val succesfulHandDetector = mockk<HandDetector>()
        val mockHand = mockk<Hand>()
        every { succesfulHandDetector(any()) } returns mockHand

        val uncalledHandDetector = mockk<HandDetector>()

        val getBestHand = createBestHandGetter(
            handDetectors = listOf(
                failingHandDetector1,
                failingHandDetector2,
                succesfulHandDetector,
                uncalledHandDetector
            ),
            getHighestCard = mockk()
        )
        val hand = getBestHand(mockSevenCards)

        verifyOrder {
            failingHandDetector1(mockSevenCards)
            failingHandDetector2(mockSevenCards)
            succesfulHandDetector(mockSevenCards)
        }
        verify(exactly = 0) { uncalledHandDetector(any()) }

        assertThat(hand).isEqualTo(mockHand)
    }

    @Test
    fun `If all hand detectors return null, return 'highest card' hand`() {
        val mockSevenCards = mockk<SevenCards>()

        val failingHandDetector = mockk<HandDetector>()
        every { failingHandDetector(any()) } returns null

        val mockHighestCardGetter = mockk<HighestCardGetter>()
        val mockHighestCard = mockk<HighestCard>()
        every { mockHighestCardGetter(mockSevenCards) } returns mockHighestCard

        val getBestHand = createBestHandGetter(
            handDetectors = listOf(failingHandDetector),
            getHighestCard = mockHighestCardGetter
        )
        val hand = getBestHand(mockSevenCards)

        assertThat(hand).isEqualTo(mockHighestCard)
    }
}
