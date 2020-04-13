import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

internal class CardKtTest {
    @Test
    fun `can create numbered spades card`() {
        val card = Card.fromString("2S")
        assertThat(card.suit).isEqualTo(SPADES)
        assertThat(card.value).isEqualTo(2)
    }

    @Test
    fun `can create numbered diamonds card`() {
        val card = Card.fromString("3D")
        assertThat(card.suit).isEqualTo(DIAMONDS)
        assertThat(card.value).isEqualTo(3)
    }
}