package aoc2020.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class FooTest {

    @Test
    fun `part 1 test`() {
        val data = """
        """.trimIndent()
            .split("\n")
        Assertions.assertEquals(0, Foo.solve(data))
    }

    @Test
    fun `part 1 real`() {
        val data = File("src/test/resources/aoc2020/day3/input.txt")
            .readLines()
        Assertions.assertEquals(0, Foo.solve(data))
    }

    @Test
    fun `part 2 test`() {

    }

    @Test
    fun `part 2 real`() {

    }

}