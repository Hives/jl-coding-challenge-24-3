class SevenCards private constructor(val cards: Set<Card>) {
    companion object {
        fun from(input: String): SevenCards {
            val cards = input.split(" ").map { Card.from(it) }.toSet()
            if (cards.size != 7) {
                throw Exception("must be called with seven cards")
            }
            return SevenCards(cards)
        }
    }
}