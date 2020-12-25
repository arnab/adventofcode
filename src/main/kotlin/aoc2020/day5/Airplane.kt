package aoc2020.day5

import aoc.extensions.splitAtIndex
import java.lang.IllegalArgumentException

object Airplane {

    fun findSeat(seatSpec: String): Int {
        val (rowSpec, colSpec) = seatSpec.splitAtIndex(7)
        val row = findSeatRecursively(rowSpec, IntRange(0, 127), lowerIndicator = "F", upperIndicator = "B")
        val col = findSeatRecursively(colSpec, IntRange(0, 7), lowerIndicator = "L", upperIndicator = "R")
        return row * 8 + col
    }

    fun findSeatWithKeyInsight(seatSpec: String): Int {
        val (rowSpec, colSpec) = seatSpec
            .replace("B", "1")
            .replace("F", "0")
            .replace("L", "0")
            .replace("R", "1")
            .splitAtIndex(7)
        val row = rowSpec.toInt(2)
        val col = colSpec.toInt(2)
        return row * 8 + col
    }

    private fun findSeatRecursively(
        seatSpec: String, range: IntRange, lowerIndicator: String, upperIndicator: String
    ): Int {
        if (range.first == range.last) return range.first

        val mid = (range.last - range.first) / 2
        val (next, remaining) = seatSpec.splitAtIndex(1)
        return when(next) {
            lowerIndicator -> findSeatRecursively(
                remaining, range.first..(range.first + mid), lowerIndicator, upperIndicator
            )
            upperIndicator -> findSeatRecursively(
                remaining, (range.last - mid)..range.last, lowerIndicator, upperIndicator
            )
            else -> throw IllegalArgumentException("Unknown indicator: $next")
        }
    }
}
