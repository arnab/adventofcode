package aoc2020.day11

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class GameOfLifeTest {

    private val exampleInput = """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day11/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(GameOfLife.parse(exampleInput), 37)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(GameOfLife.parse(problemInput), 37)
    }

    private fun verifyPart1(cells: List<List<GameOfLife.Cell>>, expectedCount: Int) {
        val (generations, stableStateCells) = GameOfLife.simulateUntilStable(cells)
        val count = GameOfLife.countSeats(stableStateCells, GameOfLife.State.OCCUPIED)
        println("Stabilized after $generations generations.")
        Assertions.assertEquals(expectedCount, count)
    }

}
