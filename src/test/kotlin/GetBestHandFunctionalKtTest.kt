import assertk.assertThat
import assertk.assertions.isInstanceOf
import hands.Flush
import hands.FourOfAKind
import hands.FullHouse
import hands.HighestCard
import hands.SinglePair
import hands.Straight
import hands.StraightFlush
import hands.ThreeOfAKind
import hands.TwoPairs
import org.junit.jupiter.api.Test

internal class GetBestHandFunctionalKtTest {
    @Test
    fun `highest card if no better hand`() {
        val hand = getBestHand(SevenCards.from("2C 4S 6H 8D 10C QS AH"))
        assertThat(hand).isInstanceOf(HighestCard::class)
    }

    @Test
    fun `can detect a pair`() {
        val hand = getBestHand(SevenCards.from("2C 4S 6H 8D 10C QS QH"))
        assertThat(hand).isInstanceOf(SinglePair::class)
    }

    @Test
    fun `chooses two pairs over one pair`() {
        val hand = getBestHand(SevenCards.from("2C 4S 6H 8D 8C QS QH"))
        assertThat(hand).isInstanceOf(TwoPairs::class)
    }

    @Test
    fun `chooses three of a kind over a pair`() {
        val hand = getBestHand(SevenCards.from("2C 4S 6H 8D QC QS QH"))
        assertThat(hand).isInstanceOf(ThreeOfAKind::class)
    }

    @Test
    fun `chooses a straight over three of a kind`() {
        val hand = getBestHand(SevenCards.from("2C 3S 4H 5D 6C 6S 6H"))
        assertThat(hand).isInstanceOf(Straight::class)
    }

    @Test
    fun `chooses a flush over a straight`() {
        val hand = getBestHand(SevenCards.from("2C 3C 6C 7C 8C 9D 10H"))
        assertThat(hand).isInstanceOf(Flush::class)
    }

    @Test
    fun `chooses a full house three of a kind`() {
        val hand = getBestHand(SevenCards.from("2C 2D 2H 3C 3D 3H 10S"))
        assertThat(hand).isInstanceOf(FullHouse::class)
    }

    @Test
    fun `chooses four of a kind over a full house`() {
        val hand = getBestHand(SevenCards.from("2C 2D 2H 3C 3D 3H 3S"))
        assertThat(hand).isInstanceOf(FourOfAKind::class)
    }

    @Test
    fun `chooses straight flush over a flush`() {
        val hand = getBestHand(SevenCards.from("2C 3C 4C 5C 6C 7C 8C"))
        assertThat(hand).isInstanceOf(StraightFlush::class)
    }
}