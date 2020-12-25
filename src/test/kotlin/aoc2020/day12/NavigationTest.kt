package aoc2020.day12

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.math.abs

internal class NavigationTest {

    private val exampleInput = """
        F10
        N3
        F7
        R90
        F11
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day12/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verify(Navigation.parse(exampleInput), 25)
    }

    @Test
    fun `part 1 real`() {
        verify(Navigation.parse(problemInput), 759)
    }

    private fun verify(turns: List<Navigation.Turn>, expectedAnswer: Int) {
        val shipAtFinalPosition = Navigation.navigate(turns)
        val manhattanDistance = abs(shipAtFinalPosition.currentX) + abs(shipAtFinalPosition.currentY)
        Assertions.assertEquals(manhattanDistance, expectedAnswer)
    }
}
