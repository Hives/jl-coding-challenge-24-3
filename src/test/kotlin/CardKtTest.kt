import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CardKtTest {

    @Nested
    @DisplayName("Suits")
    inner class Suits {
        @Test
        fun `can create spades card`() {
            val card = Card.from("2S")
            assertThat(card.suit).isEqualTo(SPADES)
        }

        @Test
        fun `can create diamonds card`() {
            val card = Card.from("2D")
            assertThat(card.suit).isEqualTo(DIAMONDS)
        }

        @Test
        fun `can create hearts card`() {
            val card = Card.from("2H")
            assertThat(card.suit).isEqualTo(HEARTS)
        }

        @Test
        fun `can create clubs card`() {
            val card = Card.from("2C")
            assertThat(card.suit).isEqualTo(CLUBS)
        }

        @Test
        fun `throws error if suit does not exist`() {
            assertThrows<Exception> { Card.from("2X") }
        }
    }

    @Nested
    @DisplayName("Card values")
    inner class Values {
        @Test
        fun `can create a 2 card`() {
            val card = Card.from("2C")
            assertThat(card.value).isEqualTo(2)
        }

        @Test
        fun `can create a 10 card`() {
            val card = Card.from("10D")
            assertThat(card.value).isEqualTo(10)
        }

        @Test
        fun `Jack has value of 11`() {
            val card = Card.from("JD")
            assertThat(card.value).isEqualTo(11)
        }

        @Test
        fun `Queen has value of 12`() {
            val card = Card.from("QD")
            assertThat(card.value).isEqualTo(12)
        }

        @Test
        fun `King has value of 13`() {
            val card = Card.from("KD")
            assertThat(card.value).isEqualTo(13)
        }

        @Test
        fun `Ace has value of 14`() {
            val card = Card.from("AD")
            assertThat(card.value).isEqualTo(14)
        }

        @Test
        fun `throws error if unrecognised face`() {
            assertThrows<Exception> { Card.from("XS") }
        }

        @Test
        fun `can represent a low ace`() {
            val card = Card(LOW_ACE, CLUBS)
            assertThat(card.value).isEqualTo(1)
        }

        @Test
        @Disabled
        fun `throws error if number not in range 2-10`() {
            assertThrows<Exception> { Card.from("1S") }
        }
    }
}