package hands

import Face

sealed class Hand
data class ThreeOfAKind(val value: Face) : Hand()
data class TwoPairs(val higherValue: Face, val lowerValue: Face) : Hand()
data class SinglePair(val value: Face) : Hand()