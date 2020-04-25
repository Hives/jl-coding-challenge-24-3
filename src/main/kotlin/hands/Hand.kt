package hands

import Card

sealed class Hand

data class StraightFlush(val cards: List<Card>) : Hand()
data class FourOfAKind(val four: Set<Card>) : Hand()
data class FullHouse(val three: Set<Card>, val pair: Set<Card>) : Hand()
data class Flush(val cards: List<Card>) : Hand()
data class Straight(val cards: List<Card>) : Hand()
data class ThreeOfAKind(val three: Set<Card>) : Hand()
data class TwoPairs(val higherPair: Set<Card>, val lowerPair: Set<Card>) : Hand()
data class Pair(val pair: Set<Card>) : Hand()
data class HighestCard(val cards: List<Card>) : Hand()