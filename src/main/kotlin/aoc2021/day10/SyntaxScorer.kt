package aoc2021.day10

object SyntaxScorer {
    fun parse(data: String) = data.split("\n")

    private val openingTokens = listOf('(', '[', '{', '<')
    private val closingTokens = listOf(')', ']', '}', '>')

    fun calculateErrorScore(data: List<String>): Int {
        val errors = mutableMapOf<Char, Int>()

        data.forEach next@{ line ->
            val inProgress = ArrayDeque<Char>()

            line.forEach { token ->
                if (token in openingTokens)
                    inProgress.addLast(token)
                else if (closingTokens.indexOf(token) == openingTokens.indexOf(inProgress.last()))
                    inProgress.removeLast()
                else {
                    errors.merge(token, 1, Int::plus)
                    return@next
                }
            }
        }

        return errors.map { (token, count)  ->
            when (token) {
                ')' -> 3 * count
                ']' -> 57 * count
                '}' -> 1197 * count
                '>' -> 25137 * count
                else -> throw IllegalArgumentException("Woah! Unknown token: $token")
            }
        }.sumBy { it }
    }

    fun autoCompleteAndScore(data: List<String>): Long {
        val autocompleteScores = mutableListOf<Long>()

        data.map next@{ line ->
            val incomplete = ArrayDeque<Char>()

            line.forEach { token ->
                if (token in openingTokens)
                    incomplete.addLast(token)
                else if (closingTokens.indexOf(token) == openingTokens.indexOf(incomplete.last()))
                    incomplete.removeLast()
                else {
                    return@next
                }
            }

            autocompleteScores.add(
                incomplete.reversed()
                    .map { token -> closingTokens[openingTokens.indexOf(token)] }
                    .map { autoCompleteScore(it) }
                    .fold(0) { total, points -> total * 5 + points }
            )
        }

        return autocompleteScores.sorted()[autocompleteScores.size/2]
    }

    private fun autoCompleteScore(token: Char) = when (token) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> throw IllegalArgumentException("Woah! Unknown token: $token")
    }
}
