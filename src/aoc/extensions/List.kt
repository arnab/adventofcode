package aoc.extensions

import kotlin.collections.List

fun <E> List<E>.possiblyNegativeLookup(i: Int): E {
    return if (i < 0) this[this.size + i] else this[i]
}

fun <E> List<E>.findFirstDuplicate(): E? {
    val counts = mutableMapOf<E, Int>()
    this.forEach {
        if (counts.containsKey(it))
            counts.put(it, counts[it]!! + 1)
        else
            counts.put(it, 1)
    }

    return this.find { counts[it]!! > 1 }
}
