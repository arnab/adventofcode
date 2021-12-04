package aoc2021.day4

object Bingo {

    data class Game(
        val steps: List<Int>,
        val boards: List<Board>
    )

    data class Board(
        val id: Int,
        val numbers: List<List<Int>>
    ) {
        companion object {
            fun from(id: Int, data: String): Board {
                val numbers = data.split("\n").map { row ->
                    row.trim().split("\\s+".toRegex()).map { it.toInt() }
                }

                return Board(id, numbers)
            }
        }
    }

    fun parse(data: String): Game {
        val steps = data.split("\n").first().split(",").map { it.toInt() }
        val allBoards = data.split("\n").drop(2).joinToString("\n")
        val boards = allBoards.split("\n\n").mapIndexed() { i, boardData -> Board.from(i+1, boardData) }
        return Game(steps, boards)
    }
}
