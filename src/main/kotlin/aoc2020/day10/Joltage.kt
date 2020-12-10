package aoc2020.day10

object Joltage {
    fun parse(data: String): List<Int> = data.split("\n").map { it.toInt() }

    fun arrange(adapters: List<Int>): List<Int> {
        val sorted = adapters.sorted()
        return sorted + listOf(sorted.maxOrNull()!! + 3)
    }

    fun calculateDiffs(chain: List<Int>): List<Int> {
        val base = listOf(0) + chain
        val pairs: List<Pair<Int, Int>> = base.zip(chain)
        return pairs.map { (prev, next) -> next - prev }
    }

    fun solvePart1(differences: List<Int>): Int {
        val diffOnes = differences.filter { it == 1 }.size
        val diffThrees = differences.filter { it == 3 }.size
        return diffOnes * diffThrees
    }

}
