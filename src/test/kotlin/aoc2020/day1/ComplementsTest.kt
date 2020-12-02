package aoc2020.day1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ComplementsTest {

    @Test
    fun `day 1 part 1 test`() {
        val data = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent()
            .split("\n").map { it.toInt() }
        Assertions.assertEquals(514579, Complements.solve(data))
    }

    @Test
    fun `day 1 part 1 real`() {
        val data = File("src/test/resources/aoc2020/day1/input.txt").readLines().map { it.toInt() }
        Assertions.assertEquals(436404, Complements.solve(data))
    }

    @Test
    fun `day 1 part 2 test`() {
        val data = """
            1721
            979
            366
            299
            675
            1456
        """.trimIndent()
            .split("\n").map { it.toInt() }
        Assertions.assertEquals(241861950, Complements.solve2(data))
    }

    @Test
    fun `day 1 part 2 real`() {
        val data = File("src/test/resources/aoc2020/day1/input.txt").readLines().map { it.toInt() }
        Assertions.assertEquals(274879808, Complements.solve2(data))
    }

}