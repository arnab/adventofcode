package aoc2016.day2

import java.util.logging.Level
import java.util.logging.Logger

data class FancyLocation(val x: Int, val y: Int) {
    /**
     * Safe builder. If x or y are out of bound, it returns null.
     */
    companion object {
        fun of(x: Int, y: Int): FancyLocation? {
            if (x < 0 || x >= FancyNumPad.keys.size) return null
            if (y < 0 || y >= FancyNumPad.keys.first().size) return null

            if (FancyNumPad.keys[x][y].isBlank()) return null

            return FancyLocation(x, y)
        }
    }
}

object FancyNumPad {
    private val logger = Logger.getLogger(this.javaClass.name)

    /**
     * Keys arranged in the natural keypad order:
     *      1
     *    2 3 4
     *  5 6 7 8 9
     *    A B C
     *      D
     */
    val keys = arrayOf(
            arrayOf("", "", "5", "", ""),
            arrayOf("", "A", "6", "2", ""),
            arrayOf("D", "B", "7", "3", "1"),
            arrayOf("", "C", "8", "5", ""),
            arrayOf("", "", "9", "", "")
    )

    // "5" or (1,1)
    val initialLocation = FancyLocation(0, 2)
    var latestLocation = initialLocation

    fun keyAt(location: FancyLocation): String = keys[location.x][location.y]
    fun keyAtCurrentLocation(): String = keyAt(latestLocation)

    /**
     * switches latestLocation to the next location based on the given direction.
     */
    fun move(direction: Direction) {
        val nextLocation = when(direction) {
            Direction.U -> FancyLocation.of(latestLocation.x, latestLocation.y + 1)
            Direction.D -> FancyLocation.of(latestLocation.x, latestLocation.y - 1)
            Direction.L -> FancyLocation.of(latestLocation.x - 1, latestLocation.y)
            Direction.R -> FancyLocation.of(latestLocation.x + 1, latestLocation.y)
        } ?: latestLocation

        logger.fine {
            "$direction from ${keyAtCurrentLocation()} ($latestLocation) -> ${keyAt(nextLocation)} ($nextLocation)"
        }
        latestLocation = nextLocation
    }
}

object FancyLooKey {
    private val logger = Logger.getLogger(this.javaClass.name)

    fun  decipher(data: String): String {

        val instructions: List<List<Direction>> = data.lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .map { it.split(Regex("")) }
                .map { it.filter(String::isNotBlank) }
                .map { it.map(this::parseDirection) }

        instructions.forEachIndexed { i, dirs -> logger.fine { "Instructions: #$i of ${instructions.size}: $dirs" } }

        val code: List<String> = instructions.map(this::applyDirections)
        logger.info { "Code: $code" }

        return code.joinToString("")
    }

    private fun parseDirection(s: String): Direction = Direction.valueOf(s)

    private fun applyDirections(directions: List<Direction>): String {
        logger.info { "Applying instructions: $directions" }
        directions.forEach { FancyNumPad.move(it) }
        logger.info { "Current Key: ${FancyNumPad.keyAtCurrentLocation()}" }

        return FancyNumPad.keyAtCurrentLocation()
    }
}
