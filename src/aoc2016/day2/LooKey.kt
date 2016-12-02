package aoc2016.day2

import java.util.logging.Logger

enum class Direction {
    U, R, D, L
}

object LooKey {
    val logger = Logger.getLogger(this.javaClass.name)

    fun  decipher(instructions: String): Int {

        val directions: List<List<Direction>> = instructions.lines()
                .map(String::trim)
                .filterNotNull()
                .map { it.split(Regex("")) }
                .map { it.filter(String::isNotBlank) }
                .map { it.map(this::parseDirection) }
        logger.info { "$directions" }

        return 1
    }

    private fun parseDirection(s: String): Direction {
        return Direction.valueOf(s)
    }

}
