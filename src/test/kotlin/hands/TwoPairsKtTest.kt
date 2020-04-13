package hands

import KING
import QUEEN
import Spots
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class TwoPairsKtTest {
    @Test
    fun `it should return null if there are no pairs`() {
        assertThat(
            "2D 3D 4D 5D 6S 7S 8S".toCards().getTwoPairs()
        ).isNull()
    }

    @Test
    fun `it should return null if there is one pair`() {
        assertThat(
            "2D 3D 4D 5D 6S 6D 8S".toCards().getTwoPairs()
        ).isNull()
    }

    @Test
    fun `it should return Two Pairs if there are two pairs`() {
        assertThat(
            "4D 5D 6S 6D 8S KD KS".toCards().getTwoPairs()
        ).isEqualTo(
            TwoPairs(KING, Spots(6))
        )
    }

    @Test
    fun `it should return the best Two Pairs if there are three pairs`() {
        assertThat(
            "QD QD 6S 6D 8S KD KS".toCards().getTwoPairs()
        ).isEqualTo(
            TwoPairs(KING, QUEEN)
        )
    }

}