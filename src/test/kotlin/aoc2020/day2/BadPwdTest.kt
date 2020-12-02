package aoc2020.day2

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class BadPwdTest {
    private val pwdSpecPattern = Regex("""(\d+)-(\d+) (\w): (\w+)""")

    @Test
    fun `part 1 test`() {
        val data = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()
            .split("\n")
            .mapNotNull { it.buildPwdSpec() }
        Assertions.assertEquals(2, BadPwd.solve(data))
    }

    @Test
    fun `part 1 real`() {
        val data = File("src/test/resources/aoc2020/day2/input.txt")
            .readLines()
            .mapNotNull { it.buildPwdSpec() }
        Assertions.assertEquals(416, BadPwd.solve(data))
    }

    @Test
    fun `part 2 test`() {
        val data = """
            1-3 a: abcde
            1-3 b: cdefg
            2-9 c: ccccccccc
        """.trimIndent()
            .split("\n")
            .mapNotNull { it.buildPwdSpec() }
        Assertions.assertEquals(1, BadPwd.solve2(data))
    }

    @Test
    fun `part 2 real`() {
        val data = File("src/test/resources/aoc2020/day2/input.txt")
            .readLines()
            .mapNotNull { it.buildPwdSpec() }
        Assertions.assertEquals(688, BadPwd.solve2(data))
    }

    private fun String.buildPwdSpec(): BadPwd.PwdSpec? {
        val matches = pwdSpecPattern.matchEntire(this)
        val (start, end, char, chars) = matches?.destructured ?: return null
        return BadPwd.PwdSpec(
            range = IntRange(start.toInt(), end.toInt()),
            char = char.toCharArray().first(),
            chars = chars
        )
    }
}
