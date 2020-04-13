package hands

import Spots
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class SinglePairKtTest {

    @Test
    fun `it should return null if there are no pairs`() {
        assertThat(
            "2D 3D 4D 5D 6S 7S 8S".toCards().getSinglePair()
        ).isNull()
    }

    @Test
    fun `it should return a pair if there is one pair`() {
        val pair = "2D 3D 4D 5D 6S 6D 8S".toCards().getSinglePair()!!
        assertThat(pair).isEqualTo(SinglePair(Spots(6)))
    }
}