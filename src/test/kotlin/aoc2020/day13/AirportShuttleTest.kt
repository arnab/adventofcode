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
        verifyPart1(AirportShuttle.parse(exampleInput), 295)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(AirportShuttle.parse(problemInput), 156)
    }

    private fun verifyPart1(timeAndBusses: Pair<Int, List<AirportShuttle.Bus>>, expectedAnswer: Int) {
        val (earliestTime, busses) = timeAndBusses
        val (earliestPossibleTime, bus) = AirportShuttle.findEarliestPossibleBus(earliestTime, busses)!!
        Assertions.assertEquals((earliestPossibleTime - earliestTime) * bus.id, expectedAnswer)
    }

}
