package aoc2016.day8

data class Cell(val x: Int, val y: Int, var on: Boolean = false) {
    fun draw() = if (on) "☃" else "-"
}

data class Board(val size: Pair<Int, Int>) {
    val cells: List<List<Cell>> by lazy {
        IntRange(0, size.second - 1).map { x->
            IntRange(0, size.first - 1).map { y->
                Cell(x, y)
            }
        }
    }

    // "rect 3x3"
    private val rectInstrMatcher = Regex("""rect (\d)+x(\d+)""")

    // "rotate row y=0 by 4"
    private val rotateRowInstrMatcher = Regex("""rotate row y=(\d+) by (\d+)""")

    // "rotate column x=1 by 1"
    private val rotateColInstrMatcher = Regex("""rotate column x=(\d+) by (\d+)""")

    fun apply(instructionText: String): Board {
        val rectMatch = rectInstrMatcher.matchEntire(instructionText)
        if (rectMatch != null) {
            val (a, b) = rectMatch.destructured
            applyRectInstruction(a.toInt(), b.toInt())
        }

        val rotateRowMatch = rotateRowInstrMatcher.matchEntire(instructionText)
        if (rotateRowMatch != null) {
            val (row, shift) = rotateRowMatch.destructured
            applyRotateRowInstruction(row.toInt(), shift.toInt())
        }

        val rotateColMatch = rotateColInstrMatcher.matchEntire(instructionText)
        if (rotateColMatch != null) {
            val (col, shift) = rotateColMatch.destructured
            applyRotateColInstruction(col.toInt(), shift.toInt())
        }

        return this
    }

    private fun applyRectInstruction(a: Int, b: Int) {
        IntRange(0, b - 1).forEach { x ->
            IntRange(0, a - 1).forEach { y ->
                cells[x][y].on = true
            }
        }
    }

    private fun applyRotateRowInstruction(row: Int, shift: Int) {
        // ☃ ☃ ☃ -  - -  -
        // -  -  - - ☃ ☃ ☃ 4 _> 1 (7 - 4 + 4
        val size = cells[row].size
        val rowOriginal = cells[row].map { it.copy() }
        cells[row].forEachIndexed { i, cell ->
            val indexBeingShifted = (size - shift + i) % 7
            cell.on = rowOriginal[indexBeingShifted].on
        }
    }

    private fun applyRotateColInstruction(col: Int, shift: Int) {
        // TODO
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
