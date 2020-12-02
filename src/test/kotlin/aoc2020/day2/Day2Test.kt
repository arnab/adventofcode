package aoc2020.day2

import aoc2020.day2.Day2
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class Day2Test {
    @Test
    fun `part 1 test`() {
        val data = """
        """.trimIndent()
            .split("\n").map { it.toInt() }
        Assertions.assertEquals(0, Day2.solve(data))
    }

    @Test
    fun `part 1 real`() {
        val data = File("src/test/resources/aoc2020/day2/input.txt").readLines().map { it.toInt() }
        Assertions.assertEquals(0, Day2.solve(data))
    }

    @Test
    fun `part 2 test`() {
        val data = """
        """.trimIndent()
            .split("\n").map { it.toInt() }
        Assertions.assertEquals(0, Day2.solve2(data))
    }

    @Test
    fun `part 2 real`() {
        val data = File("src/test/resources/aoc2020/day2/input.txt").readLines().map { it.toInt() }
        Assertions.assertEquals(0, Day2.solve2(data))
    }

}
