package hands

import Card
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

internal class PairsKtTest {

    @Test
    fun `it should return null if there are no pairs`() {
        val hand = pairsOrNull(SevenCards.from("2D 3D 4D 5D 6S 7S 8S"))
        assertThat(hand).isNull()
    }

    @Test
    fun `it should return a pair if there is one pair`() {
        val hand = pairsOrNull(SevenCards.from("2D 3D 4D 5D 6S 6D 8S")) as Pair
        assertThat(hand.pair).containsOnly(Card.from("6S"), Card.from("6D"))
    }

    @Test
    fun `it should return two pairs if there are two pairs`() {
        val hand = pairsOrNull(SevenCards.from("2D 3D 3H 5D 6S 6D 8S")) as TwoPairs
        assertThat(hand.higherPair).containsOnly(Card.from("6S"), Card.from("6D"))
        assertThat(hand.lowerPair).containsOnly(Card.from("3D"), Card.from("3H"))
    }


    @Test
    fun `it should return the highest two pairs if there are more than two pairs`() {
        val hand = pairsOrNull(SevenCards.from("3D 3H 2S 2C 6S 6D AD")) as TwoPairs
        assertThat(hand.higherPair).containsOnly(Card.from("6S"), Card.from("6D"))
        assertThat(hand.lowerPair).containsOnly(Card.from("3D"), Card.from("3H"))
    }
}