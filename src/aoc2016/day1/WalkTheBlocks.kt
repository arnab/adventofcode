package aoc2016.day1

enum class Turn { R, L }
data class Move(val turn: Turn, val blocks: Int)

enum class Direction { N, E, S, W }

data class Position(val heading: Direction, val x: Int, val y: Int) {
    fun travel(move: Move): Position {
        println("pos: $this move: $move")
        return Position(Direction.N, 0, 0)
    }

    fun distanceFrom(other: Position): Int {
        // FIXME
        return 1
    }
}


object WalkTheBlocks {
    private val movesSeperatorPattern = Regex(""",\s+""")
    private val turnAndBlocksPattern = Regex("""([RL])(\d+)""")

    fun calculateShortestPathDistance(moves: String): Int {
        val initialPosition = Position(Direction.N, x = 0, y = 0)
        val finalPosition = parseMoves(moves).fold(initialPosition, Position::travel)

        return finalPosition.distanceFrom(initialPosition)
    }

    private fun parseMoves(moves: String): List<Move> {
        return moves.split(movesSeperatorPattern)
                .map(String::trim)
                .map { m -> parseTurnAndNumBlocks(m) }
                .filterNotNull()
    }

    private fun parseTurnAndNumBlocks(move: String): Move? {
        val matches = turnAndBlocksPattern.matchEntire(move)
        val (turn, numBlocks) = matches?.destructured ?: return null
        return Move(Turn.valueOf(turn), numBlocks.toInt())
    }
}
