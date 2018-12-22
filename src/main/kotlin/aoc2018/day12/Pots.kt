package aoc2018.day12

data class Pots(val initialState: String, val rules: Map<CharSequence, CharSequence>) {

    fun sumAfterNGenerations(numGenerations: Int): Long {
        val seen = mutableMapOf<String, Pair<Long, Long>>()
        var offset = 0L
        var state = initialState
        var i = 0L
        while (i < numGenerations) {
            seen[state] = i to offset
            state = "....$state....".windowed(5) { rules.getOrElse(it) { "." } }
                    .joinToString(separator = "")
                    .dropLastWhile { it != '#' }
                    .apply { offset += indexOf('#') - 2 }
                    .dropWhile { it != '#' }
            i++
            val (previousI, previousOffset) = seen[state] ?: continue
            offset += (numGenerations - i) / (i - previousI) * (offset - previousOffset)
            i = numGenerations - (numGenerations - i) % (i - previousI)
        }
        return state.withIndex().fold(0L) { acc, (i, c) -> if (c == '#') acc + offset + i else acc }
    }

}
