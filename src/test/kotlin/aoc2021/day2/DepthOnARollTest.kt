package aoc2021.day2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class DepthOnARollTest {

    private val exampleInput = """
        forward 5
        down 5
        forward 8
        up 3
        down 8
        forward 2
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day2/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(DepthOnARoll.parse(exampleInput), 150)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(DepthOnARoll.parse(problemInput), 2039256)
    }

    private fun verifyPart1(data: List<DepthOnARoll.Command>, expectedAnswer: Int) {
        Assertions.assertEquals(DepthOnARoll.calculate(data), expectedAnswer)
    }
}
