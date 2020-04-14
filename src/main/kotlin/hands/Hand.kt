package hands

import Card
import Face

sealed class Hand
data class FourOfAKind(val four: List<Card>) : Hand()
data class Straight(val highestCard: Face) : Hand()
data class ThreeOfAKind(val three: List<Card>) : Hand()
data class TwoPairs(val higherPair: List<Card>, val lowerPair: List<Card>) : Hand()
data class SinglePair(val pair: List<Card>) : Hand()