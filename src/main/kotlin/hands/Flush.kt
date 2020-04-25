package hands

import SevenCards

fun SevenCards.getFlush(getFlushFrom: GetFlushFrom = ::getFlushFrom): Flush? =
    getFlushFrom(this.cards)?.let { Flush(it) }