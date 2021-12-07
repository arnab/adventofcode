package aoc2021.day7

import kotlin.math.abs

object CrabSubmarine {
    fun parse(data: String) = data.split("\n").map { it.toInt() }

    fun findFuelCostForMedian(crabs: List<Int>): Int {
        val sortedCrabs = crabs.sorted()
        val midIndex = sortedCrabs.size / 2
        return (midIndex - 1..midIndex + 1).map { i ->
            calculateFuelCostToMoveTo(crabs, sortedCrabs[i])
        }.minOf { it }
    }

    private fun calculateFuelCostToMoveTo(crabs: List<Int>, destination: Int) = crabs.sumOf { abs(it - destination) }
}

