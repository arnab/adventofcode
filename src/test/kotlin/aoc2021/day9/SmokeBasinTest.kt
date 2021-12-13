package aoc2021.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class SmokeBasinTest {

    private val exampleInput = """
            2199943210
            3987894921
            9856789892
            8767896789
            9899965678
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day9/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(SmokeBasin.parse(exampleInput), 15)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(SmokeBasin.parse(problemInput), 1)
    }

    private fun verifyPart1(locations: Map<SmokeBasin.Loc, Int>, expectedAnswer: Int) {
        Assertions.assertEquals(SmokeBasin.calculateRiskScore(locations), expectedAnswer)
    }
}
