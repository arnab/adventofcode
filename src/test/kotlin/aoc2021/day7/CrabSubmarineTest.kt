package aoc2021.day7

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class CrabSubmarineTest {

    private val exampleInput = """
        16,1,2,0,4,2,7,1,2,14
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day7/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(CrabSubmarine.parse(exampleInput), 37)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(CrabSubmarine.parse(problemInput), 347449)
    }

    private fun verifyPart1(crabs: List<Int>, expectedAnswer: Int) {
        Assertions.assertEquals(CrabSubmarine.findFuelCostForMedian(crabs), expectedAnswer)
    }

    @Test
    fun `part 2 test`() {
        verifyPart2(CrabSubmarine.parse(exampleInput), 168)
    }

    @Test
    fun `part 2 real`() {
        verifyPart2(CrabSubmarine.parse(problemInput), 98039527)
    }

    private fun verifyPart2(crabs: List<Int>, expectedAnswer: Int) {
        Assertions.assertEquals(CrabSubmarine.findFuelCostWithInflation(crabs), expectedAnswer)
    }
}
