package aoc2018.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MarbleManiaTest {

    @Test
    fun part1_HighScore_Problem1() {
        val (numPlayers, lastMarblePoints)  = parseData("405 players; last marble is worth 71700 points")
        Assertions.assertEquals(428690, MarbleMania.calculateHighScore(numPlayers, lastMarblePoints))
    }

    @ParameterizedTest
    @CsvSource(
            "10 players; last marble is worth 1618 points, 8317",
            "13 players; last marble is worth 7999 points, 146373",
            "17 players; last marble is worth 1104 points, 2764",
            "21 players; last marble is worth 6111 points, 54718",
            "30 players; last marble is worth 5807 points, 37305"
    )
    fun part1_HighScore_Examples(data: String, expected: String) {
        val (numPlayers, lastMarblePoints) = parseData(data)
        val expectedHighScore = expected.toLong()
        Assertions.assertEquals(expectedHighScore, MarbleMania.calculateHighScore(numPlayers, lastMarblePoints))
    }

    @Test
    fun part2_HighScore_Problem1() {
        Assertions.assertEquals(3628143500, MarbleMania.calculateHighScore(405, 71700 * 100))
    }

    private val dataLineRegex = """(\d+) players; last marble is worth (\d+) points*""".toRegex()
    private fun parseData(data: String): Pair<Int, Int> {
        val (numPlayers, lastMarblePoints) = dataLineRegex.find(data)!!.destructured
        return Pair(numPlayers.toInt(), lastMarblePoints.toInt())
    }

}
