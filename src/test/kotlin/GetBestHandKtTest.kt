import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import hands.HighestCard
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class GetBestHandKtTest {
    @Test
    fun `if all hand detectors fail, return highest card, with the five highest cards in descending order`() {
        val mockHandDetector = mockk<HandDetector>()
        every { mockHandDetector(any()) } returns null

        val getBestHand = createBestHandGetter(listOf(mockHandDetector))
        val sevenCards = SevenCards.from("2D 3C 4S 5H JC QD KS")
        val hand = getBestHand(sevenCards)

        assertThat(hand).isInstanceOf(HighestCard::class)
        assertThat((hand as HighestCard).cards).isEqualTo(
            listOf(
                Card.from("KS"),
                Card.from("QD"),
                Card.from("JC"),
                Card.from("5H"),
                Card.from("4S")
            )
        )
    }
}