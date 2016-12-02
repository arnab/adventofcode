package aoc.extensions

import kotlin.collections.List

fun <E> List<E>.possiblyNegativeLookup(i: Int): E {
    return if (i < 0) this[this.size + i] else this[i]
}
