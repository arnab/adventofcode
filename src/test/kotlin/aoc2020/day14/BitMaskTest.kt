package aoc2020.day14

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class BitMaskTest {

    private val exampleInput = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day14/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(exampleInput, 165)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(problemInput, 14954914379452)
    }

    private fun verifyPart1(data: String, expectedAnswer: Long) {
        Assertions.assertEquals(BitMask.part1(data.split("\n")), expectedAnswer)
    }
}
