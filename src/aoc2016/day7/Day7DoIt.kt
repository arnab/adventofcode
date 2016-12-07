package aoc2016.day7

object Day7DoIt {
    fun doit(data: String): Int {
        val (matches, rejected) = data
                .lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .partition(this::supportsTls)

        println("Following ${matches.count()} strings matched:")
        matches.forEach { println("\t ✔ $it") }

        println("Following ${rejected.count()} strings DID NOT match:")
        rejected.forEach { println("\t ✘ $it") }

        return matches.count()
    }

    val abaPattern = Regex("""([a-z])([a-z])\1""")

    fun supportsTls(data: String): Boolean {
        println("Testing: $data")

        var matches: MutableList<MatchResult> = mutableListOf()

        for (i in 0 until data.count() - 6) {
            val subData = data.subSequence(i, data.count())
//            println("\t Looking at sub str: $subData")
            matches.addAll(abaPattern.findAll(subData).toList())
        }

        val uniqueMatches = matches.groupBy { it.groups.first()?.value }.values.map { it.first() }
        val supportsSsl = uniqueMatches.any { match -> assessMatch(match, data) }

        if (supportsSsl)
            println("Confirmed: Supports SSL: $data")
        else
            println("Denied: DOES NOT Supports SSL: $data")


        return supportsSsl
    }

    private fun assessMatch(match: MatchResult, data: String): Boolean {
        val (str, a, b) = match.groupValues

        println("Testing Match: $str")

        if (a == b) {
            println("false: Same repeating chars ($a, $b)")
            return false
        }

        val bracketsNonMatchPattern = Regex("\\[\\w*$a$b$a\\w*\\]")

        val bracketsNonMatchPatternMatch = bracketsNonMatchPattern.find(data)
        if (bracketsNonMatchPatternMatch != null && bracketsNonMatchPatternMatch.groups.size > 0) {
            println("false: ABA pattern found inside []!!!")
            return false
        }

//        val babOutsideBracketsPattern = Regex("($b$a$b)(?![^\\[]*\\])")
//
//        val babOutsideBracketsPatternMatch = babOutsideBracketsPattern.find(data)
//        if (babOutsideBracketsPatternMatch != null && babOutsideBracketsPatternMatch.groups.size > 0) {
//            println("false: BAB pattern found outside []!!!")
//            return false
//        }

        val bracketsPattern = Regex("\\[\\w*$b$a$b\\w*\\]")

        val bracketsPatternMatch = bracketsPattern.find(data)
        if (bracketsPatternMatch != null && bracketsPatternMatch.groups.size > 0) {
            println("true: BAB pattern found!!!")
            return true
        }

        println("false: by default!!!")
        return false
    }
}
