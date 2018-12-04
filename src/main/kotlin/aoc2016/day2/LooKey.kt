package aoc2016.day2

import java.util.logging.Logger

data class Location(val x: Int, val y: Int) {
    /**
     * Safe builder. If x or y are out of bound, it returns null.
     */
    companion object {
        fun of(x: Int, y: Int): Location? {
            if (x < 0 || x >= NumPad.keys.size) return null
            if (y < 0 || y >= NumPad.keys.first().size) return null

            return Location(x, y)
        }
    }
}

object NumPad {
    private val logger = Logger.getLogger(this.javaClass.name)

    /**
     * Keys arranged in the natural keypad order. So, (1,0): 8, (0,1): 4, (2,2): 3 etc.:
     *   1 2 3
     *   4 5 6
     *   7 8 9
     */
    val keys = arrayOf(
            intArrayOf(7, 4, 1),
            intArrayOf(8, 5, 2),
            intArrayOf(9, 6, 3)
    )

    // "5" or (1,1)
    val initialLocation = Location(1, 1)
    var latestLocation = initialLocation

    fun keyAt(location: Location): Int = keys[location.x][location.y]
    fun keyAtCurrentLocation(): Int = keyAt(latestLocation)

    /**
     * switches latestLocation to the next location based on the given direction.
     */
    fun move(direction: Direction) {
        val nextLocation = when(direction) {
            Direction.U -> Location.of(latestLocation.x, latestLocation.y + 1)
            Direction.D -> Location.of(latestLocation.x, latestLocation.y - 1)
            Direction.L -> Location.of(latestLocation.x - 1, latestLocation.y)
            Direction.R -> Location.of(latestLocation.x + 1, latestLocation.y)
        } ?: latestLocation

        logger.fine {
            "$direction from ${keyAtCurrentLocation()} ($latestLocation) -> ${keyAt(nextLocation)} ($nextLocation)"
        }
        latestLocation = nextLocation
    }
}

object LooKey {
    private val logger = Logger.getLogger(this.javaClass.name)

    fun  decipher(data: String): Int {

        val instructions: List<List<Direction>> = data.lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .map { it.split(Regex("")) }
                .map { it.filter(String::isNotBlank) }
                .map { it.map(this::parseDirection) }

        instructions.forEachIndexed { i, dirs -> logger.fine { "Instructions: #$i of ${instructions.size}: $dirs" } }

        val code: List<Int> = instructions.map(this::applyDirections)
        logger.info { "Code: $code" }

        return code.joinToString("").toInt()
    }

    private fun parseDirection(s: String): Direction = Direction.valueOf(s)

    private fun applyDirections(directions: List<Direction>): Int {
        logger.info { "Applying instructions: $directions" }
        directions.forEach { NumPad.move(it) }
        logger.info { "Current Key: ${NumPad.keyAtCurrentLocation()}" }

        return NumPad.keyAtCurrentLocation()
    }

}
