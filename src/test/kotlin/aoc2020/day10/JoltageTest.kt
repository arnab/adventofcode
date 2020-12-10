package aoc2020.day10

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class JoltageTest {
    private val exampleInput = """
            16
            10
            15
            5
            1
            11
            7
            19
            6
            12
            4
        """.trimIndent()

    private val example2Input = """
            28
            33
            18
            42
            31
            14
            46
            20
            48
            47
            24
            23
            49
            45
            19
            38
            39
            11
            1
            32
            25
            35
            8
            17
            7
            9
            4
            2
            34
            10
            3
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day10/input.txt").readText()

    @Test
    fun `part 1 test`() {
        val adapters = Joltage.parse(exampleInput)
        val chain = Joltage.arrange(adapters)
        val differences = Joltage.calculateDiffs(chain)
        val answerPart1 = Joltage.solvePart1(differences)
        Assertions.assertEquals(35, answerPart1)
    }

    @Test
    fun `part 1 test2`() {
        val adapters = Joltage.parse(example2Input)
        val chain = Joltage.arrange(adapters)
        val differences = Joltage.calculateDiffs(chain)
        val answerPart1 = Joltage.solvePart1(differences)
        Assertions.assertEquals(220, answerPart1)
    }

    @Test
    fun `part 1 real`() {
        val adapters = Joltage.parse(problemInput)
        val chain = Joltage.arrange(adapters)
        val differences = Joltage.calculateDiffs(chain)
        val answerPart1 = Joltage.solvePart1(differences)
        Assertions.assertEquals(2812, answerPart1)
    }

    @Test
    fun `part 2 test`() {
        val adapters = Joltage.parse(exampleInput).toSet()
        Assertions.assertEquals(8, Joltage.countPaths(adapters))
    }

    @Test
    fun `part 2 test2`() {
        val adapters = Joltage.parse(example2Input).toSet()
        Assertions.assertEquals(19208, Joltage.countPaths(adapters))
    }

    @Test
    fun `part 2 real`() {
        val adapters = Joltage.parse(problemInput).toSet()
        Assertions.assertEquals(386869246296064, Joltage.countPaths(adapters))
    }

}
