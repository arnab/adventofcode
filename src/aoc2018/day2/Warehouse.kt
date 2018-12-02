package aoc2018.day2

object Warehouse {

    fun boxesWithCountByLetters(boxIds: List<List<String>>): List<Map<String, Int>> {
        return boxIds.map { it.groupBy { char -> char }.map { it.key to it.value.size }.toMap() }
    }

    fun calculateChecksum(boxesWithCountByLetters: List<Map<String, Int>>): Int {
        val boxesWithTwoDuplicateLetters = boxesWithCountByLetters.filter { it.values.contains( 2 ) }.size
        val boxesWithThreeDuplicateLetters = boxesWithCountByLetters.filter { it.values.contains( 3 ) }.size
        return boxesWithTwoDuplicateLetters * boxesWithThreeDuplicateLetters
    }

}