package hands

import Spots
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class ThreeOfAKindKtTest {
    @Test
    fun `returns null if there aren't three of a kind`() {
        assertThat(
            "2D 3D 4D 5D 6S 7S 8S".toCards().getThreeOfAKind()
        ).isNull()
    }

    @Test
    fun `returns three of a kind if there are three of a kind`() {
        assertThat(
            "2D 3D 4D 5D 6S 6S 6S".toCards().getThreeOfAKind()
        ).isEqualTo(ThreeOfAKind(Spots(6)))
    }

    @Test
    fun `returns the best three of a kind if there are two`() {
        assertThat(
            "2D 2D 2D 5D 6S 6S 6S".toCards().getThreeOfAKind()
        ).isEqualTo(ThreeOfAKind(Spots(6)))
    }
}