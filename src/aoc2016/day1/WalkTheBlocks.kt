package aoc2016.day1

enum class Turn { R, L }
data class Move(val turn: Turn, val blocks: Int)

enum class Direction { N, E, S, W }

data class Position(val heading: Direction, val x: Int, val y: Int) {
    fun travel(move: Move): Position {
        // FIXME
        return Position(Direction.N, 0, 0)
    }

    fun distanceFrom(other: Position): Int {
        // FIXME
        return 1
    }
}


object WalkTheBlocks {
    fun calculateShortestPathDistance(moves: String): Int {
        val initialPosition = Position(Direction.N, x = 0, y = 0)
        val finalPosition = parseMoves(moves).fold(initialPosition, Position::travel)

        return finalPosition.distanceFrom(initialPosition)
    }

    private fun parseMoves(moves: String): List<Move> {
        println(moves)
        // FIXME
        return listOf(Move(Turn.R, 1), Move(Turn.L, 3))
    }
}
