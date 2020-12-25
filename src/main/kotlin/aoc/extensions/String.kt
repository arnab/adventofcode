package aoc.extensions

import kotlin.String

fun String.splitAtIndex(index : Int) = take(index) to substring(index)
