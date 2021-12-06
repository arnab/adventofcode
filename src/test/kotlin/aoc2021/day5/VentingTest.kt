package aoc2021.day5

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class VentingTest {

    private val exampleInput = """
        0,9 -> 5,9
        8,0 -> 0,8
        9,4 -> 3,4
        2,2 -> 2,1
        7,0 -> 7,4
        6,4 -> 2,0
        0,9 -> 2,9
        3,4 -> 1,4
        0,0 -> 8,8
        5,5 -> 8,2
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day5/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(Venting.parse(exampleInput), 5)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(Venting.parse(problemInput), 6311)
    }

    private fun verifyPart1(lines: List<Pair<Venting.Point, Venting.Point>>, expectedAnswer: Int) {
        Assertions.assertEquals(Venting.countDangerousPoints(lines), expectedAnswer)
    }
}
