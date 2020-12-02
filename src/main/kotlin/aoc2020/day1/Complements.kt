package aoc2020.day1

object Complements {
    fun solve(data: List<Int>): Int {
        val complements = data.associateBy({ it }, { 2020 - it })
        val match = complements.filterValues {
            data.contains(it)
        }.entries.first()

        return match.key * match.value
    }

    fun solve2(data: List<Int>): Int {
        val complementOfPairs = data.map { x -> data.minus(x).map { y -> Pair(x, y) } }
            .flatten()
            .associateBy({ it }, { 2020 - (it.first + it.second) })

        val match = complementOfPairs.filterValues {
            data.contains(it)
        }.entries.first()

        return match.key.first * match.key.second *match.value
    }
}
