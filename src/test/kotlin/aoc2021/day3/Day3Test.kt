package aoc2021.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day3Test {

    private val exampleInput = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day3/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(Day3.parse(exampleInput), 198)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(Day3.parse(problemInput), 3277364)
    }

    private fun verifyPart1(readings: List<List<String>>, expectedAnswer: Int) {
        Assertions.assertEquals(Day3.calculatePowerConsumption(readings), expectedAnswer)
    }

    @Test
    fun `part 2 test`() {
        verifyPart2(Day3.parse(exampleInput), 230)
    }

    @Test
    fun `part 2 real`() {
        verifyPart2(Day3.parse(problemInput), 5736383)
    }

    private fun verifyPart2(readings: List<List<String>>, expectedAnswer: Int) {
        Assertions.assertEquals(Day3.calculateLifeSupport(readings), expectedAnswer)
    }
}
