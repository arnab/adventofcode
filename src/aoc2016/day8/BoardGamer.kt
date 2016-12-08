package aoc2016.day8

data class Cell(var on: Boolean = false) {
    fun draw() = if (on) "☃" else "-"
}

data class Board(val size: Pair<Int, Int>) {
    private val cells by lazy { setup() }

    private fun setup(): List<List<Cell>> {
        val (X, Y) = size
        return IntRange(0, X).map { IntRange(0, Y).map { Cell() } }
    }

    fun apply(instruction: String): Board {
        // TODO
        return this
    }

    fun draw() {
        cells.forEach { row ->
            println()
            println(row.map(Cell::draw).joinToString { " " })
        }
    }
}

object BoardGamer {
    fun run(X: Int, Y: Int, instructions: List<String>): Board {
        var board = Board(Pair(X, Y))
        instructions.forEach { board = board.apply(it) }
        return board
    }
}
