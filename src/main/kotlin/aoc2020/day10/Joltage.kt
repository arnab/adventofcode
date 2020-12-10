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

    fun solvePart1(differences: List<Int>): Int =
        differences.groupingBy { it }
            .eachCount()
            .let { diffsCount -> diffsCount[1]!! * diffsCount[3]!! }

    /**
     * Cache of known paths for the given adapter, to avoid re-calculation of trillions of possibilities.
     */
    private val knownPaths: MutableMap<Int, Long> = mutableMapOf()

    fun countPaths(adapters: Set<Int>): Long {
        knownPaths.clear()
        return countPathsRecursively(adapters, 0, adapters.maxOrNull()!!)
    }

    private fun countPathsRecursively(adapters: Set<Int>, currentAdapter: Int, maxAdapter: Int): Long {
        // No more paths
        if (currentAdapter == maxAdapter) return 1

        // memoize the path-count for the current adapter
        knownPaths.getOrPut(currentAdapter) {
            countPathsForAdapter(adapters, currentAdapter, maxAdapter)
        }
        return knownPaths[currentAdapter]!!
    }

    private fun countPathsForAdapter(adapters: Set<Int>, adapter: Int, maxAdapter: Int): Long =
        listOfNotNull(
            (adapter + 1).let { if (it in adapters) countPathsRecursively(adapters, it, maxAdapter) else null },
            (adapter + 2).let { if (it in adapters) countPathsRecursively(adapters, it, maxAdapter) else null },
            (adapter + 3).let { if (it in adapters) countPathsRecursively(adapters, it, maxAdapter) else null }
        ).sum()
}
