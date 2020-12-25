package aoc2020.day13

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class AirportShuttleTest {

    private val exampleInput = """
            939
            7,13,x,x,59,x,31,19
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day13/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(AirportShuttle.parse(exampleInput), 295L)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(AirportShuttle.parse(problemInput), 156L)
    }

    @Test
    fun `part 2 test`() {
        verifyPart2(AirportShuttle.parse(exampleInput), 1068781L)
    }

    @Test
    fun `part 2 real`() {
        verifyPart2(AirportShuttle.parse(problemInput), 404517869995362)
    }

    private fun verifyPart1(timeAndBusses: Pair<Int, List<AirportShuttle.Bus>>, expectedAnswer: Long) {
        val (earliestTime, busses) = timeAndBusses
        val (earliestPossibleTime, bus) = AirportShuttle.findEarliestPossibleBus(earliestTime, busses)!!
        Assertions.assertEquals((earliestPossibleTime - earliestTime) * bus.id, expectedAnswer)
    }

    private fun verifyPart2(timeAndBusses: Pair<Int, List<AirportShuttle.Bus>>, expectedAnswer: Long) {
        val earliestPossibleTime = AirportShuttle.findEarliestPossibleTime(timeAndBusses.second)
        Assertions.assertEquals(earliestPossibleTime, expectedAnswer)
    }

}
