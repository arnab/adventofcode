package aoc2016.day8

import kotlin.properties.Delegates

data class Cell(val x: Int, val y: Int) {
    var on: Boolean by Delegates.observable(false) {
        _, old, new ->
//        println("DEBUG: Changing $this.on: $old -> $new")
    }

    fun draw() = if (on) "☃" else "-"

    override fun toString() = "Cell(x=$x, y=$y, on=$on)"
}

data class Board(val size: Pair<Int, Int>) {
    val cells: List<List<Cell>> by lazy {
        IntRange(0, size.first).map { x->
            IntRange(0, size.second).map { y->
                Cell(x, y)
            }
        }
    }

    private val rectInstrMatcher = Regex("""rect (\d)+x(\d+)""")

    fun apply(instructionText: String): Board {
        val rectMatch = rectInstrMatcher.matchEntire(instructionText)
        if (rectMatch != null) {
            val (a, b) = rectMatch.destructured
            applyRectInstruction(a.toInt(), b.toInt())
        }
        return this
    }

    private fun  applyRectInstruction(a: Int, b: Int) {
        IntRange(0, a).forEach { x ->
            IntRange(0, b).forEach { y ->
                cells[x][y].on = true
            }
        }
    }

    fun draw() {
        cells.forEach { row ->
            println()
            println(row.map(Cell::draw).joinToString(" "))
        }
    }
}

object BoardGamer {
    fun run(X: Int, Y: Int, instructions: List<String>): Board {
        var board = Board(Pair(X, Y))
        instructions.forEach {
            println("Applying instruction \"$it\" on current board:")
            board.draw()
            board = board.apply(it)
        }
        println("Final board, after all instructions:")
        board.draw()

        return board
    }
}
