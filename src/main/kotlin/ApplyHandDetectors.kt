import hands.Hand
import hands.HighestCard

typealias HandDetectorApplier = (SevenCards) -> Hand
typealias HandDetector = (SevenCards) -> Hand?
typealias HighestCardGetter = (SevenCards) -> HighestCard

fun createHandDetectorApplier(handDetectors: List<HandDetector>, getHighestCard: HighestCardGetter): HandDetectorApplier {
    return fun(sevenCards: SevenCards): Hand =
        handDetectors.fold(null as Hand?) { handOrNull, nextHandDetector ->
            handOrNull ?: nextHandDetector(sevenCards)
        } ?: getHighestCard(sevenCards)
}