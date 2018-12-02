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

    fun reportPrototypeBoxes(boxes: List<List<String>>): Unit {
        boxes.forEach { box ->
            val protypeBoxes = boxes.filter { otherBox -> isProtypeBoxOf(box, otherBox) }
            if (protypeBoxes.isNotEmpty()) {
                println("Box: ${box} => Prototypes: ${protypeBoxes}")
            }
        }
    }

    private fun isProtypeBoxOf(box: List<String>, otherBox: List<String>): Boolean {
        val lettersDifferring = box.filterIndexed { i, letter ->
            letter != otherBox.get(i)
        }

        return lettersDifferring.size == 1
    }
}