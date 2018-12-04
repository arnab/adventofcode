package aoc2016.day8

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BoardGamerTest {
    fun boardWithInstructionsFromFile(x: Int, y: Int, instructionsFile: String): Board {
        val instructions = TestResourceReader.readFile(instructionsFile)
                .lines().map(String::trim).filter(String::isNotBlank)
        return boardWithInstructions(x, y, instructions)
    }

    fun boardWithInstructions(x: Int, y: Int, instructions: List<String>) = BoardGamer.run(x, y, instructions)

    fun totalLit(board: Board) = board.cells.sumBy { row -> row.sumBy { c -> if (c.on) 1 else 0 } }

    @Test fun run_Example() {
        val instructions = listOf(
                "rect 3x2",
                "rotate column x=1 by 1",
                "rotate row y=0 by 4",
                "rotate column x=1 by 1"
        )

        val board = boardWithInstructions(7, 3, instructions)
        assertEquals(6, totalLit(board))

        val cellStates = board.cells.map { it.map(Cell::draw).joinToString("") }
        val expectedCellStates = listOf(
                "-#--#-#",
                "#-#----",
                "-#-----"
        )
        assertEquals(expectedCellStates, cellStates)
    }

    @Test fun run_Problem() {
        val board = boardWithInstructionsFromFile(50, 6, "aoc2016/day8/input.txt")
        assertEquals(110, totalLit(board))
    }
}
