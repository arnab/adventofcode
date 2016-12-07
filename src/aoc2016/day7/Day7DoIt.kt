package aoc2016.day7

object Day7DoIt {
    fun doit(data: String): Int {
        val matches = data
                .lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .filter(this::supportsTls)
        println("Following ${matches.count()} strings matched:")
        matches.forEach { println("\t$it") }

        return matches.count()
    }

    val abbaPattern = Regex("""([a-z])([a-z])\2\1""")


    fun  supportsTls(data: String): Boolean {
        println("Testing $data")

        val matches = abbaPattern.findAll(data)

        if (matches.count() == 0) {
            println("false: No matches")
            return false
        }

        matches.forEach { match ->
            val (str, a, b) = match.groupValues

            if (a == b) {
                println("false: Same repeating chars ($a, $b)")
                return false
            }

            val bracketsPattern = Regex("\\[\\w*$str\\w*\\]")

            val bracketsPatternMatch = bracketsPattern.find(data)
            if (bracketsPatternMatch != null && bracketsPatternMatch.groups.size > 0) {
                println("false: Matches bracket pattern")
                return false
            }
        }

        println("MATCH FOUND!!!")
        matches.forEach { m ->
            println("\t${m.groupValues.first()}")
        }

        return true
    }
}
