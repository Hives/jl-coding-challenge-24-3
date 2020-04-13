import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class HandsKtTest {

    @Nested
    @DisplayName("getFlush")
    inner class getFlush {

        @Test
        fun `returns null if no flush exists`() {
            assertThat(
                "2D 3D 4D 5D 6S 7S 8S".toCards().getFlush()
            ).isNull()
        }

        @Test
        fun `returns a flush of hearts`() {
            assertThat(
                "2D 3D 4H 5H 6H 7H 8H".toCards().getFlush()!!
            ).containsOnly(
                Card(Number(4), HEARTS),
                Card(Number(5), HEARTS),
                Card(Number(6), HEARTS),
                Card(Number(7), HEARTS),
                Card(Number(8), HEARTS)
            )
        }

        @Test
        fun `returns a flush of diamonds`() {
            assertThat(
                "2D 3D 4H 5H 6D 7D 8D".toCards().getFlush()!!
            ).containsOnly(
                Card(Number(2), DIAMONDS),
                Card(Number(3), DIAMONDS),
                Card(Number(6), DIAMONDS),
                Card(Number(7), DIAMONDS),
                Card(Number(8), DIAMONDS)
            )
        }

        @Test
        fun `if more than five cards of a suit it returns the best flush`() {
            assertThat(
                "2C 3C 4C 5C 6C 7C 8C".toCards().getFlush()!!
            ).containsOnly(
                Card(Number(4), CLUBS),
                Card(Number(5), CLUBS),
                Card(Number(6), CLUBS),
                Card(Number(7), CLUBS),
                Card(Number(8), CLUBS)
            )
        }
    }
}