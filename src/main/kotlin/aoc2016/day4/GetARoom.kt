package aoc2016.day4

data class Room(val encryptedName: String, val sectorId: Int, val checkSum: String) {
    val name: String by lazy { decryptName() }

    val computedCheckSum: String by lazy { computeCheckSum() }

    private fun  decryptName(): String = encryptedName
                .split("-")
                .map { w -> w.map { c -> rotateBy(c, sectorId) } }
                .map { w -> w.joinToString("") }
                .joinToString(" ")

    private fun rotateBy(c: Char, n: Int): Char {
        val indexAfterRotation = (aToz.indexOf(c) + n) % aToz.size
        return aToz[indexAfterRotation]
    }

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
        private val aToz: List<Char> = ('a'..'z').toList()

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
    fun filterValidRooms(data: String): List<Room> = data
            .lines()
            .map(String::trim)
            .filter(String::isNotBlank)
            .map { Room.from(it) }
            .filterNotNull()
            .filter(Room::isReal)

    fun sumOfValidRoomSectorIds(data: String) = filterValidRooms(data).sumBy(Room::sectorId)
}
