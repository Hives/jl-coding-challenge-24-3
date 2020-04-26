import hands.Hand
import hands.HighestCard

typealias BestHandGetter = (SevenCards) -> Hand
typealias HandDetector = (SevenCards) -> Hand?
typealias HighestCardGetter = (SevenCards) -> HighestCard

fun createBestHandGetter(
    handDetectors: List<HandDetector>,
    getHighestCard: HighestCardGetter
): BestHandGetter {
    return fun(sevenCards: SevenCards): Hand =
        handDetectors.fold(null as Hand?) { handOrNull, nextHandDetector ->
            handOrNull ?: nextHandDetector(sevenCards)
        } ?: getHighestCard(sevenCards)
}