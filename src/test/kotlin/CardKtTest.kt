import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `can create numbered hearts card`() {
        val card = Card.fromString("4H")
        assertThat(card.suit).isEqualTo(HEARTS)
        assertThat(card.value).isEqualTo(4)
    }

    @Test
    fun `can create numbered clubs card`() {
        val card = Card.fromString("5C")
        assertThat(card.suit).isEqualTo(CLUBS)
        assertThat(card.value).isEqualTo(5)
    }

    @Test
    fun `throws error if suit does not exist`() {
        assertThrows<Exception> { Card.fromString("6X") }
    }

}