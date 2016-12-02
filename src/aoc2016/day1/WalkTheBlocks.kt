package aoc2016.day1

import java.lang.Math.abs

enum class Turn { R, L }
data class Move(val turn: Turn, val blocks: Int)

enum class Direction { N, E, S, W }

data class Location(val x: Int, val y: Int) {
    fun distanceFrom(other: Location): Int {
        val distance = abs(other.x - x) + abs(other.y - y)

        println("Distance of: $this, from: $other -> $distance")
        return distance
    }
}

data class Position(val heading: Direction, val loc: Location) {
    private var recorder: TravelRecorder? = null

    fun travel(move: Move): Position {
        val nextHeading = calculateNextHeading(heading, move.turn)
        val nextPosition = calculateNextPosition(nextHeading, move.blocks)

        println("Travel: $this + $move -> $nextPosition")
        return nextPosition
    }

    fun distanceFrom(other: Position): Int {
        val distance = loc.distanceFrom(other.loc)

        println("Distance of: $this, from: $other -> $distance")
        return distance
    }

    val directionsInRightTurnOrder = listOf(Direction.N, Direction.E, Direction.S, Direction.W)

    private fun calculateNextHeading(heading: Direction, turn: Turn): Direction {
        val currentIndex = directionsInRightTurnOrder.indexOf(heading)
        var nextIndex = currentIndex + if (turn == Turn.R) 1 else -1
        if (nextIndex >= directionsInRightTurnOrder.size)
            nextIndex = directionsInRightTurnOrder.size - nextIndex
        if (nextIndex < 0)
            nextIndex += directionsInRightTurnOrder.size

        return directionsInRightTurnOrder.get(nextIndex)
    }

    private fun calculateNextPosition(nextHeading: Direction, blocks: Int): Position {
        return when (nextHeading) {
            Direction.N -> Position(nextHeading, Location(loc.x, loc.y + blocks))
            Direction.S -> Position(nextHeading, Location(loc.x, loc.y - blocks))
            Direction.E -> Position(nextHeading, Location(loc.x + blocks, loc.y))
            Direction.W -> Position(nextHeading, Location(loc.x - blocks, loc.y))
        }
    }
}

class TravelRecorder(val locationsVisited: List<Location>) {

}

object WalkTheBlocks {
    private val movesSeparatorPattern = Regex(""",\s+""")
    private val turnAndBlocksPattern = Regex("""([RL])(\d+)""")

    private val startingLocation = Location(0, 0)

    fun calculateShortestPathDistance(moves: String): Int {
        val startingPosition = Position(Direction.N, startingLocation)
        val finalPosition = parseMoves(moves).fold(startingPosition, Position::travel)

        return finalPosition.distanceFrom(startingPosition)
    }

    fun findFirstRepeatedLocation(moves: String): Position {
        val travelRecorder = TravelRecorder(listOf(startingLocation))
        val startingPosition = Position(Direction.N, startingLocation)
        parseMoves(moves).fold(startingPosition, Position::travel)
        println("All locations visited: ${travelRecorder.locationsVisited}")

        return startingPosition
    }

    private fun parseMoves(moves: String): List<Move> {
        return moves.split(movesSeparatorPattern)
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
