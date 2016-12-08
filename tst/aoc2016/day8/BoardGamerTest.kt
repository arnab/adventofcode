package aoc2016.day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BoardGamerTest {
    @Test fun run_Example() {
        val board = BoardGamer.run(5, 5, emptyList())
        board.draw()
    }
}
