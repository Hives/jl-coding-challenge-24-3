fun getFlush(cards: List<Card>): List<Card>? =
    if (cards.count { it.suit == HEARTS } == 5) {
        cards.filter { it.suit == HEARTS }
    } else {
        null
    }