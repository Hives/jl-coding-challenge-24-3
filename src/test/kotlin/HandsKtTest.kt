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
                getFlush("2D 3D 4D 5D 6S 7S 8S".toCards())
            ).isNull()
        }

        @Test
        fun `returns a flush of hearts`() {
            assertThat(
                getFlush("2D 3D 4H 5H 6H 7H 8H".toCards())!!
            ).containsOnly(
                Card(Number(4), HEARTS),
                Card(Number(5), HEARTS),
                Card(Number(6), HEARTS),
                Card(Number(7), HEARTS),
                Card(Number(8), HEARTS)
            )
        }
    }
}