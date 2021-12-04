package aoc2021.day4

object Bingo {

    data class Slot(val num: Int, val marked: Boolean = false)

    data class Board(
        val id: Int,
        val slots: List<List<Slot>>
    ) {
        companion object {
            fun from(id: Int, data: String): Board {
                val numbers = data.split("\n").map { row ->
                    row.trim().split("\\s+".toRegex()).map { Slot(it.toInt()) }
                }

                return Board(id, numbers)
            }
        }

        fun mark(step: Int): Board {
            val markedSlots = slots.map { slotRow ->
                slotRow.map { slot ->
                    val shouldMark = slot.marked || slot.num == step
                    Slot(slot.num, shouldMark)
                }
            }
            return Board(id, markedSlots)
        }

        fun isWinner(): Boolean {
            val anyRowFullyMarked = slots.any { slotRow -> slotRow.all { it.marked } }
            if (anyRowFullyMarked) return true

            val columnIndexes = IntRange(0, slots.first().size - 1)
            val anyColumnFullyMarked = columnIndexes.any { columnIndex ->
                val column = slots.map { it[columnIndex] }
                column.all { it.marked }
            }
            return anyColumnFullyMarked
        }
    }

    data class Game(
        val steps: List<Int>,
        val boards: List<Board>
    ) {
        fun solvePart1(): Int {
            var nextStepBoards = boards
            steps.forEach { step ->
                nextStepBoards = play(step, nextStepBoards)
                val winner = findWinner(nextStepBoards)
                if (winner != null) {
                    return calculateAnswer(winner, step)
                }
            }

            throw RuntimeException("Woah! No winner!")
        }

        private fun play(step: Int, boards: List<Board>) = boards.map { it.mark(step) }

        private fun findWinner(boards: List<Board>) = boards.find { it.isWinner() }

        private fun calculateAnswer(board: Board, step: Int): Int {
            val sumOfAllUnmarked = board.slots.flatten().filter { !it.marked }.sumOf { it.num }
            return sumOfAllUnmarked * step
        }

        fun solvePart2(): Int {
            var nextStepBoards = boards
            val remainingSteps = steps.dropWhile { step ->
                nextStepBoards = play(step, nextStepBoards).filterNot { it.isWinner() }
                nextStepBoards.size != 1
            }

            var lastBoard = nextStepBoards.first()
            remainingSteps.forEach { step ->
                lastBoard = play(step, listOf(lastBoard)).first()
                if (lastBoard.isWinner()) {
                    return calculateAnswer(lastBoard, step)
                }
            }

            throw RuntimeException("Woah! No winner!")
        }
    }

    fun parse(data: String): Game {
        val steps = data.split("\n").first().split(",").map { it.toInt() }
        val allBoards = data.split("\n").drop(2).joinToString("\n")
        val boards = allBoards.split("\n\n").mapIndexed() { i, boardData -> Board.from(i+1, boardData) }
        return Game(steps, boards)
    }
}
