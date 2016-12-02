package aoc2016.day2

import java.util.logging.Logger

enum class Direction { U, R, D, L }

data class Location(val x: Int, val y: Int)

object NumPad {
    private val logger = Logger.getLogger(this.javaClass.name)

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
            Direction.U -> Location(latestLocation.x, latestLocation.y + 1)
            else -> latestLocation
        }

        logger.info {
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
                .filterNotNull()
                .map { it.split(Regex("")) }
                .map { it.filter(String::isNotBlank) }
                .map { it.map(this::parseDirection) }

        val code: List<Int> = instructions.map(this::applyDirections)
        logger.info { "Code: $code" }

        return code.joinToString("").toInt()
    }

    private fun parseDirection(s: String): Direction = Direction.valueOf(s)

    private fun applyDirections(directions: List<Direction>): Int {
        logger.info { "Applying instructions: $directions" }
        directions.forEach { NumPad.move(it) }
        return NumPad.keyAtCurrentLocation()
    }

}
