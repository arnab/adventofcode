package aoc2021.day6

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class LanternfishTest {

    private val exampleInput = """
        3,4,3,1,2
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day6/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(Lanternfish.parse(exampleInput), 1)
    }

    @Test
    fun `part 1 real`() {
        val initialSchool = Lanternfish.parse(problemInput)
        val after80Days = Lanternfish.calculateSchoolSize(initialSchool, days = 80)
        Assertions.assertEquals(after80Days, 377263)
    }

    @Test
    fun `part 2 test`() {
        val initialSchool = Lanternfish.parse(exampleInput)
        Assertions.assertEquals(
            Lanternfish.calculateSchoolSizeRecursiveWithMemory(initialSchool, days = 256),
            26984457539
        )
    }

    @Test
    fun `part 2 real`() {
        val initialSchool = Lanternfish.parse(problemInput)
        Assertions.assertEquals(
            Lanternfish.calculateSchoolSizeRecursiveWithMemory(initialSchool, days = 256),
            1695929023803
        )
    }

    private fun verifyPart1(initialPopulation: List<Lanternfish.Fish>, expectedAnswer: Int) {
        val after18Days = Lanternfish.calculateSchoolSize(initialPopulation, days = 18)
        val after80Days = Lanternfish.calculateSchoolSize(initialPopulation, days = 80)
        Assertions.assertEquals(26, after18Days)
        Assertions.assertEquals(5934, after80Days)
    }
}
