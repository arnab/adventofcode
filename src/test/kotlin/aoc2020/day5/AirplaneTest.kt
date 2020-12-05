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
    fun `part 2 real`() {
        val data = File("src/test/resources/aoc2020/day5/input.txt").readLines()
        val seats = data.map { Airplane.findSeat(it) }.sorted()

        // Seat numbers (sorted) increases by 1. So the first one that increases by 2, is the one after my seat.
        val increments = seats.mapIndexed { i, n -> n - seats[maxOf(i - 1, 0)] }
        val nearbyMissingSeatIndex = increments.indexOf(2)

        val missingSeat = seats[nearbyMissingSeatIndex] - 1
        Assertions.assertEquals(657, missingSeat)

        // Sanity checks...
        Assertions.assertEquals(577, nearbyMissingSeatIndex)
        Assertions.assertEquals(656, seats[nearbyMissingSeatIndex-1])
        Assertions.assertEquals(658, seats[nearbyMissingSeatIndex])
        Assertions.assertEquals(659, seats[nearbyMissingSeatIndex + 1])
    }

}
