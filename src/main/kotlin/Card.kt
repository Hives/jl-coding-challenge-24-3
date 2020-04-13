data class Card(val face: Face, val suit: Suit) {
    val value = face.value

    companion object {
        fun fromString(str: String): Card =
            Card(
                Face.fromString(str.dropLast(1)),
                Suit.fromChar(str.last())
            )
    }
}

sealed class Suit {
    companion object {
        fun fromChar(char: Char) = when (char) {
            'S' -> SPADES
            'D' -> DIAMONDS
            else -> throw Exception("Could not create suit from '${char}'")
        }
    }
}

object HEARTS : Suit()
object DIAMONDS : Suit()
object SPADES : Suit()
object CLUBS : Suit()

sealed class Face {
    abstract val value: Int

    companion object {
        fun fromString(str: String) = Number(str.toInt())
    }
}

data class Number(val spots: Int) : Face() {
    override val value = spots
}

data class Picture(val picture: Char) : Face() {
    override val value = 0
}