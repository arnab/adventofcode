package aoc2021.day1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ItsSoDeepAndDarkTest {

    private val exampleInput = """
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day1/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(ItsSoDeepAndDark.parse(exampleInput), 7)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(ItsSoDeepAndDark.parse(problemInput), 1266)
    }

    private fun verifyPart1(depths: List<Int>, expectedAnswer: Int) {
        Assertions.assertEquals(ItsSoDeepAndDark.countIncrements(depths), expectedAnswer)
    }

    @Test
    fun `part 2 test`() {
        verifyPart2(ItsSoDeepAndDark.parse(exampleInput), 5)
    }

    @Test
    fun `part 2 real`() {
        verifyPart2(ItsSoDeepAndDark.parse(problemInput), 1217)
    }

    private fun verifyPart2(depths: List<Int>, expectedAnswer: Int) {
        Assertions.assertEquals(ItsSoDeepAndDark.countTripleIncrements(depths), expectedAnswer)
    }
}
