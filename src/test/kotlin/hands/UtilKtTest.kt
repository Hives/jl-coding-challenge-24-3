package hands

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsOnly
import org.junit.jupiter.api.Test

internal class UtilKtTest {
    @Test
    fun `can get pairs from hand`() {
        val pairs = SevenCards.from("AS JD AD 10S 2H 2D 3C").getGroupsOfSize(2)
        assertThat(pairs).containsOnly(
            setOf(Card.from("AS"), Card.from("AD")),
            setOf(Card.from("2H"), Card.from("2D"))
        )
    }

    @Test
    fun `can get threes from hand`() {
        val pairs = SevenCards.from("AS AC AD JS 2H 2D 2C").getGroupsOfSize(3)
        assertThat(pairs).containsOnly(
            setOf(Card.from("AS"), Card.from("AD"), Card.from("AC")),
            setOf(Card.from("2H"), Card.from("2D"), Card.from("2C"))
        )
    }

    @Test
    fun `returns groups in descending order of value`() {
        val pairs = SevenCards.from("2C 2D 7C 7D 3C 3D AC").getGroupsOfSize(2)
        assertThat(pairs).containsExactly(
            setOf(Card.from("7C"), Card.from("7D")),
            setOf(Card.from("3C"), Card.from("3D")),
            setOf(Card.from("2C"), Card.from("2D"))
        )
    }
}