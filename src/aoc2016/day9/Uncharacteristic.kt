package aoc2016.day9

object Uncharacteristic {
    fun decompress(data: String): String {
        var chars = data.split("").toMutableList()
        var result = mutableListOf<String>()

        while (chars.size > 0) {
            val c = chars.removeAt(0)

            if (c == "(") {
                val (decompressed, remaining) = handleMarker(chars)
                chars = remaining
                result.addAll(decompressed)

            } else {
                result.add(c)
            }
        }

        return result.joinToString("")
    }

    private val markerMatcher = Regex("""(\d+)x(\d+)""")

    private fun handleMarker(chars: MutableList<String>): Pair<List<String>, MutableList<String>> {
        return Pair(emptyList(), mutableListOf())
    }
}
