package hands

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsOnly
import org.junit.jupiter.api.Test
import toCards

internal class UtilKtTest {
    @Test
    fun `can get pairs from hand`() {
        val pairs = "AS JD AD 10S 2H 2D 3C".toCards().getGroupsOfSize(2)
        assertThat(pairs).containsOnly(
            setOf(Card.fromString("AS"), Card.fromString("AD")),
            setOf(Card.fromString("2H"), Card.fromString("2D"))
        )
    }

    @Test
    fun `can get threes from hand`() {
        val pairs = "AS AC AD JS 2H 2D 2C".toCards().getGroupsOfSize(3)
        assertThat(pairs).containsOnly(
            setOf(Card.fromString("AS"), Card.fromString("AD"), Card.fromString("AC")),
            setOf(Card.fromString("2H"), Card.fromString("2D"), Card.fromString("2C"))
        )
    }

    @Test
    fun `returns groups in descending order of value`() {
        val pairs = "2C 2D 7C 7D 3C 3D AC".toCards().getGroupsOfSize(2)
        assertThat(pairs).containsExactly(
            setOf(Card.fromString("7C"), Card.fromString("7D")),
            setOf(Card.fromString("3C"), Card.fromString("3D")),
            setOf(Card.fromString("2C"), Card.fromString("2D"))
        )
    }
}