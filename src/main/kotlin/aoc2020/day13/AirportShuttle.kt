package aoc2020.day13

import kotlin.math.ceil

object AirportShuttle {
    fun parse(data: String): Pair<Int, List<Bus>> {
        val earliestTime = data.split("\n")[0].toInt()
        val busses = data.split("\n")[1].split(",").mapIndexedNotNull { i, n ->
            if (n == "x") null else Bus(i, n.toInt())
        }
        return Pair(earliestTime, busses)
    }

    fun findEarliestPossibleBus(earliestTime: Int, busses: List<Bus>): Pair<Int, Bus>? = busses.map {
        Pair(
            (ceil(earliestTime * 1.0 / it.id) * it.id).toInt(),
            it
        )
    }.minByOrNull { it.first }

    data class Bus(
        val index: Int,
        val id: Int
    )
}
