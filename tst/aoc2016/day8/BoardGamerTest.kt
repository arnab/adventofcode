package aoc2016.day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BoardGamerTest {
    @Test fun run_Example() {
        val instructions = listOf(
                "rect 3x2",
                "rotate column x=1 by 1",
                "rotate row y=0 by 4",
                "rotate column x=1 by 1"
        )
        val board = BoardGamer.run(7, 3, instructions)
        val totalOn = board.cells.sumBy { row ->
            row.sumBy { c -> if (c.on) 1 else 0 }
        }
        assertEquals(6, totalOn)
        val cellStates = board.cells.map { it.map(Cell::draw).joinToString("") }
        val expectedCellStates = listOf(
                "-☃--☃-☃",
                "☃-☃----",
                "-☃-----"
        )
        assertEquals(expectedCellStates, cellStates)
    }
}
