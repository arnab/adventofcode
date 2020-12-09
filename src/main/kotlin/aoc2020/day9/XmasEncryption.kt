package aoc2020.day9

object XmasEncryption {
    fun parse(data: String) = data.split("\n").map { it.toLong() }

    fun findFirstInvalid(numbers: List<Long>, preamble: Int = 25) =
        numbers.withIndex().find { (i, n) ->
            if (i < preamble) {
                false
            } else {
                val window = numbers.drop(i - preamble).take(preamble)
                !satisfies(n, window)
            }
        }?.value

    private fun satisfies(n: Long, window: List<Long>): Boolean {
        println("Looking for a match for $n in $window")
        val complements = window.associateBy({ it }, { n - it }).filter { it.key != it.value }
        val match = complements.filterValues { window.contains(it) }
        if (match.isEmpty()) {
            return false.also { println("NOT satisfied! match!") }
        }
        return true.also { println("Satisfied: $match") }
    }
}
