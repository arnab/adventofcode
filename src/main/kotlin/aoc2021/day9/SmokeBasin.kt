package aoc2021.day9

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

}
