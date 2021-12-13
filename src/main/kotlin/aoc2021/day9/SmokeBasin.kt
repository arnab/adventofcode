package aoc2021.day9

import java.util.LinkedList

object SmokeBasin {

    data class Loc(val x: Int, val y: Int)

    fun parse(data: String) = data.split("\n")
        .foldIndexed(mutableMapOf()) { row, heights: MutableMap<Loc, Int>, line ->
            line.split("").filter { it.isNotBlank() } .foldIndexed(heights) { col, heights, height ->
                heights[Loc(col, row)] = height.toInt()
                heights
            }
        }

    fun calculateRiskScore(heightByLoc: Map<Loc, Int>) = findLowPoints(heightByLoc).values.sumBy { it + 1 }

    private fun findLowPoints(heightByLoc: Map<Loc, Int>): Map<Loc, Int> = heightByLoc.filter {
        findAdjacents(it.key).filter { adjacent -> heightByLoc.containsKey(adjacent) }
            .all { adjacent -> it.value < heightByLoc[adjacent]!! }
    }

    private fun findAdjacents(loc: Loc) = listOf(
        Loc(loc.x - 1, loc.y),
        Loc(loc.x + 1, loc.y),
        Loc(loc.x, loc.y - 1),
        Loc(loc.x, loc.y + 1),
    )

    fun calculateLargeBasinsScore(heightByLoc: Map<Loc, Int>) = findLowPoints(heightByLoc).keys.asSequence()
        .map { findBasinAround(it, heightByLoc) }
        .map { it.size }
        .sortedByDescending { it }
        .take(3)
        .fold(1) { score, basinSize -> score * basinSize }

    private fun findBasinAround(start: Loc, heightByLoc: Map<Loc, Int>): Set<Loc> {
        val basin = HashSet<Loc>()

        val candidates = LinkedList(findAdjacents(start)
            .filter { adjacent -> heightByLoc.containsKey(adjacent) }
            .filter { heightByLoc[it]!! < 9 })

        while (candidates.isNotEmpty()) {
            val nextLoc = candidates.pop()
            if (heightByLoc[nextLoc]!! < 9) {
                basin.add(nextLoc)
                val secondDegreeAdjacents = findAdjacents(nextLoc)
                    .filter { adjacent -> heightByLoc.containsKey(adjacent) }
                    .filter { heightByLoc[it]!! < 9 }
                    .filter { !basin.contains(it) }
                candidates.addAll(secondDegreeAdjacents)
            }
        }
        return basin
    }

}
