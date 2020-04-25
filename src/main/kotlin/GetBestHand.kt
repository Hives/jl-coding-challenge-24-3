import hands.Hand
import hands.HighestCard

typealias BestHandGetter = (SevenCards) -> Hand

typealias HandDetector = (SevenCards) -> Hand?

fun createBestHandGetter(handDetectors: List<HandDetector>): BestHandGetter {
    return fun(sevenCards: SevenCards): Hand =
        sevenCards.cards.sortedByDescending { it.value }.take(5).let { HighestCard(it) }
}