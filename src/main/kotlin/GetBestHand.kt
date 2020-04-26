import hands.Hand
import hands.createFlushOrNull
import hands.createStraightFlushOrNull
import hands.createStraightOrNull
import hands.fourOfAKindOrNull
import hands.fullHouseOrNull
import hands.getAtLeastFiveCardsOfOneSuit
import hands.getStraightFrom
import hands.highestCard
import hands.pairsOrNull
import hands.threeOfAKindOrNull

fun getBestHand(sevenCards: SevenCards): Hand {
    val straightOrNull = createStraightOrNull(::getStraightFrom)
    val flushOrNull = createFlushOrNull(::getAtLeastFiveCardsOfOneSuit)
    val straightFlushOrNull = createStraightFlushOrNull(
        ::getAtLeastFiveCardsOfOneSuit,
        ::getStraightFrom
    )

    val applyHandDetectors = createHandDetectorApplier(
        handDetectors = listOf(
            straightFlushOrNull,
            ::fourOfAKindOrNull,
            ::fullHouseOrNull,
            flushOrNull,
            straightOrNull,
            ::threeOfAKindOrNull,
            ::pairsOrNull
        ),
        getHighestCard = ::highestCard
    )

    return applyHandDetectors(sevenCards)
}