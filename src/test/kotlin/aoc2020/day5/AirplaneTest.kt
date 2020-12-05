package aoc2020.day5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class AirplaneTest {

    private val testInput = """
        FBFBBFFRLR
        """.trimIndent()

    @Test
    fun `part 1 test`() {
        val actual = Airplane.findSeat(testInput.split("\n").first())
        Assertions.assertEquals(357, actual)
    }

    @Test
    fun `part 1 real`() {
        val data = File("src/test/resources/aoc2020/day5/input.txt").readLines()
        val highestSeat = data.map { Airplane.findSeat(it) }.maxOrNull()
        Assertions.assertEquals(926, highestSeat)
    }

    @Test
    fun `part 2 test`() {
        Assertions.assertEquals(1, 0)
    }

    @Test
    fun `part 2 real`() {
        val data = File("src/test/resources/aoc2020/day5/input.txt").readText()
        Assertions.assertEquals(1, 0)
    }

}
