package aoc2021.day1

object ItsSoDeepAndDark {
    fun parse(data: String) = data.split("\n").map { it.toInt() }

    fun countIncrements(depths: List<Int>): Int {
        val diffs = depths.foldIndexed(emptyList()) { i, acc: List<Int>, depth ->
            val prevIndex = if (i == 0) 0 else i - 1
            val diff = depth - depths[prevIndex]
            acc + diff
        }
        return diffs.filter { it > 0 }.size
    }

    fun countTripleIncrements(depths: List<Int>): Int {
        val triples = depths.mapIndexed { i, depth ->
            if (i < 2) {
                return@mapIndexed null
            } else {
                return@mapIndexed Triple(depths[i-2], depths[i-1], depth)
            }
        }
        val tripleSums = triples.filterNotNull().map { it.first + it.second + it.third }
        return countIncrements(tripleSums)
    }

}
