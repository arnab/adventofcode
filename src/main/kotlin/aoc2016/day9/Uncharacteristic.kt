package aoc2016.day9

object Uncharacteristic {
    fun decompress(data: String): String {
        var chars = data.split("").toMutableList()
        var result = mutableListOf<String>()

        while (chars.size > 0) {
            val c = chars.removeAt(0)

            if (c == "(") {
                val (decompressed, remaining) = handleMarker(chars)
                chars = remaining.toMutableList()
                result.addAll(decompressed)
            } else {
                println("DEBUG: no marker. Repeating $c")
                result.add(c)
            }
        }

        return result.joinToString("")
    }

    private val markerMatcher = Regex("""(\d+)x(\d+)\)(.*)""")

    private fun handleMarker(chars: MutableList<String>): Pair<List<String>, List<String>> {
        val markerMatch = markerMatcher.matchEntire(chars.joinToString(""))
        markerMatch ?: return Pair(emptyList(), mutableListOf())

        val numCharsToRepeat = markerMatch.groupValues[1].toInt()
        val repeatFor        = markerMatch.groupValues[2].toInt()
        val rest             = markerMatch.groupValues[3].toList()

        val charsToRepeat = rest.subList(0, numCharsToRepeat)
        val repeatedChars = (0..repeatFor - 1).map { charsToRepeat }.flatten().map(Char::toString)
        val remaining = rest.subList(numCharsToRepeat, rest.size).map(Char::toString)

        println("DEBUG: marker found: (${numCharsToRepeat} chars, $repeatFor times). To repeat: ${charsToRepeat.joinToString("")}")

        return Pair(repeatedChars, remaining)
    }
}
