package aoc2020.day5

import java.lang.IllegalArgumentException

object Airplane {

    fun findSeat(seatSpec: String): Long {
        val (rowSpec, colSpec) = seatSpec.splitAtIndex(7)
        val row = findSeatRecursively(
            rowSpec,
            IntRange(0, 127),
            lowerIndicator = "F",
            upperIndicator = "B"
        )
        val col = findSeatRecursively(
            colSpec,
            IntRange(0, 7),
            lowerIndicator = "L",
            upperIndicator = "R"
        )

        return row * 8 + col
    }

    private fun findSeatRecursively(
        seatSpec: String,
        range: IntRange,
        lowerIndicator: String,
        upperIndicator: String
    ): Long {
        if (range.first == range.last) {
            return range.first.toLong()
        }

        val (next, remaining) = seatSpec.splitAtIndex(1)
        val mid = (range.last - range.first) / 2
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

    private fun String.splitAtIndex(index : Int) = take(index) to substring(index)

}
