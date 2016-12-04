package aoc2016.day4

import java.util.logging.Logger

data class Room(val encryptedName: String, val sectorId: Int, val checkSum: String) {
    val computedCheckSum: String by lazy { computeCheckSum() }

    private fun  computeCheckSum(): String {
        val letters = encryptedName.filter(Char::isLetter)
        val frequenciesByLetter: Map<Char, Int> = letters.fold(mutableMapOf(), { part, letter ->
            part.put(letter, (part[letter] ?: 0) + 1)
            part
        })
        val lettersByFrequency: Map<Int, List<Char>> = frequenciesByLetter.toList().fold(mutableMapOf(), {
            part, (letter, frequency) ->
            part.put(frequency, ( part[frequency] ?: emptyList() ).plus(letter))
            part
        })
        val lettersSortedByFrequencyThanAlphabetic = lettersByFrequency.toList()
                .sortedByDescending { it.first }
                .map { it.second }
                .map { it.sorted() }
                .flatten()

        return lettersSortedByFrequencyThanAlphabetic.take(5).joinToString("")
    }

    fun isReal(): Boolean = checkSum == computedCheckSum

    companion object {
        private val logger = Logger.getLogger(this.javaClass.name)

        // e.g. aaaaa-bbb-z-y-x-123[abxyz]
        private val roomPattern = Regex("""([\w-]+)-(\d+)\[(\w+)\]""")

        fun from(data: String): Room? {
            val matches = roomPattern.matchEntire(data)
            val (name, sector, checkSum) = matches?.destructured ?: return null
            return Room(encryptedName = name, sectorId = sector.toInt(), checkSum = checkSum)
        }
    }
}

object GetARoom {

}
