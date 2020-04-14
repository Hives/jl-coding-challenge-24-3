package hands

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import toCards

internal class FourOfAKindKtTest {
    @Test
    fun `it should return null if there is no four of a kind`() {
        val hand = "2D 3D 4D 5D 6S 6D 6H".toCards().getFourOfAKind()
        assertThat(hand).isNull()
    }

    @Test
    fun `it should return four of a kind if there are four of a kind`() {
        val hand = "2D 3D 4D 6C 6S 6D 6H".toCards().getFourOfAKind()!!
        assertThat(hand.four).containsOnly(
            Card.fromString("6C"),
            Card.fromString("6S"),
            Card.fromString("6D"),
            Card.fromString("6H")
        )
    }
}