package aoc2016.day7

data class IpAddress(val address: String) {
    fun supportsSsl(): Boolean {
        println("Testing: $address")

        val (supernets, hypernets) = partitionSequences(address)

        println("Supernet sequences: $supernets")
        println("Hypernet sequences: $hypernets")

        val abaPatterns = findAbaMatches(supernets)
        val babPatterns = abaPatterns.map { (a, b) -> Pair(b, a) }

        println("Checking if any of the BAB patterns ($babPatterns) are in the hypernets.")
        val babInHypernets = babPatterns.any { patternFoundIn(it, hypernets) }

        println("Verifying that none of the ABA patterns ($abaPatterns) are in the hypernets.")
        val abaNotInHypernets = abaPatterns.none { patternFoundIn(it, hypernets) }

        return babInHypernets // && abaNotInHypernets

    }

    private val supernetMatcher = Regex("(\\w+)(?![^\\[]*\\])")
    private val hypernetMatcher = Regex("""\[(\w+)\]""")

    private fun partitionSequences(address: String): Pair<List<String>, List<String>> {
        val hypernets: List<String> = findAllNthMatchedGroups(hypernetMatcher, address, 1)
        val supernets: List<String> = findAllNthMatchedGroups(supernetMatcher, address, 1)
        return Pair(supernets, hypernets)
    }

    private fun findAllNthMatchedGroups(regex: Regex, s: String, matchGroup: Int): List<String> = regex.findAll(s)
            .toList()
            .map { it.groups[matchGroup]?.value!! }

    private val abaMatcher = Regex("""([a-z])([a-z])\1""")

    private fun findAbaMatches(strings: List<String>): List<Pair<Char, Char>> {
        val abaMatches = strings.map { s ->
            var matches: MutableList<MatchResult> = mutableListOf()

            for (i in 0 until s.count()) {
                val subStr = s.subSequence(i, s.count())
                matches.addAll(abaMatcher.findAll(subStr).toList())
            }

            matches.map { m ->
                val (_, a, b) = m.groupValues
                if (a != b) {
                    Pair(a.toCharArray()[0], b.toCharArray()[0])
                } else null
            }
        }

        val uniqueAbaMatches = abaMatches.flatten().filter { it != null }.map { it!! }.distinct()
        println("Found ABA patterns: $uniqueAbaMatches")
        return uniqueAbaMatches
    }

    private fun patternFoundIn(chars: Pair<Char, Char>, strings: List<String>): Boolean {
        val (a, b) = chars
        val pattern = Regex("$a$b$a")
        val stringsMatchingPattern = strings.filter { pattern.containsMatchIn(it) }

        if (stringsMatchingPattern.any())
            println("Following strings contain $pattern: $stringsMatchingPattern")
        return stringsMatchingPattern.any()
    }
}

object IPAddresses {
    fun supportsSsl(data: String): List<IpAddress> {
        val (accepted, rejected) = data.lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .map { IpAddress(it) }
                .partition(IpAddress::supportsSsl)

        println("Following ${accepted.count()} IP Addresses support SLL:")
        accepted.forEach { println("\t ✔ $it") }

        println("Following ${rejected.count()} IP Addresses DO NOT support SLL:")
        rejected.forEach { println("\t ✘ $it") }

        return accepted
    }
}
