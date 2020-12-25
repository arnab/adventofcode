package aoc.extensions

import kotlin.collections.List

fun <E> List<E>.possiblyNegativeLookup(i: Int): E = if (i < 0) this[this.size + i] else this[i % this.size]

fun <E> List<E>.findFirstDuplicate(): E? = HashSet<E>().let { set -> firstOrNull { !set.add(it) } }
