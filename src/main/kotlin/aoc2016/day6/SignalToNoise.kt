package aoc2016.day6

object SignalToNoise {
    fun findRealMessage(data: String): String {
        val rawData = data.lines()
                .map(String::trim)
                .filter(String::isNotBlank)

        return decypher(rawData)
    }

    private fun  decypher(rawData: List<String>): String {
        return 0.until(rawData.first().chars().count()).map { column ->
            val chars = rawData.map { it[column.toInt()] }.groupBy { it }
            chars.values.sortedBy { it.size }.first().first()
        }.joinToString("")
    }
}
