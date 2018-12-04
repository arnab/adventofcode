package aoc2016.day1

import aoc.extensions.findFirstDuplicate
import aoc.extensions.possiblyNegativeLookup
import java.lang.Math.abs
import java.util.logging.Logger

enum class Turn(val steps: Int) { R(1), L(-1) }
data class Move(val turn: Turn, val blocks: Int)

enum class Direction {
    N, E, S, W;

    private val order by lazy { listOf(N, E, S, W) }

    fun add(turns: Int): Direction {
        val currentIndex = order.indexOf(this)

        var nextIndex = (currentIndex + turns) % order.size
        return order.possiblyNegativeLookup(nextIndex)
    }

    fun subtract(turns: Int) = add(-1 * turns)
    fun apply(turn: Turn) = add(turn.steps)
}

data class Location(val x: Int, val y: Int) {
    companion object {
        val logger = Logger.getLogger(this.javaClass.name)
    }

    fun distanceFrom(other: Location): Int {
        val distance = abs(other.x - x) + abs(other.y - y)

        logger.info { "$this to $other -> $distance" }
        return distance
    }
}

data class Position(val heading: Direction, val loc: Location) {
    companion object {
        val logger = Logger.getLogger(this.javaClass.name)
    }

    private var recorder: TravelRecorder? = null

    fun enableTravelRecording(recorder: TravelRecorder?) {
        this.recorder = recorder
    }

    fun travel(move: Move): Position {
        val nextHeading = heading.apply(move.turn)
        val nextPosition = calculateNextPosition(nextHeading, move.blocks)

        logger.info { "$this + $move -> $nextPosition" }
        recorder?.recordTravel(loc, nextPosition.loc)
        nextPosition.enableTravelRecording(recorder)

        return nextPosition
    }

    fun distanceFrom(other: Position): Int {
        val distance = loc.distanceFrom(other.loc)

        logger.info { "$this to $other -> $distance" }
        return distance
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

class TravelRecorder(val locationsVisited: MutableList<Location>) {
    fun recordTravel(from: Location, to: Location) {
        if (from.x < to.x) {
            for(x in IntRange(from.x + 1, to.x)) locationsVisited.add(Location(x, from.y))
        }

        if (from.x > to.x) {
            for(x in from.x - 1 downTo to.x) locationsVisited.add(Location(x, from.y))
        }

        if (from.y < to.y) {
            for(y in IntRange(from.y + 1, to.y)) locationsVisited.add(Location(from.x, y))
        }

        if (from.y > to.y) {
            for(y in from.y - 1 downTo to.y) locationsVisited.add(Location(from.x, y))
        }
    }
}

object WalkTheBlocks {
    val logger = Logger.getLogger(this.javaClass.name)

    private val movesSeparatorPattern = Regex(""",\s+""")
    private val turnAndBlocksPattern = Regex("""([RL])(\d+)""")

    private val startingLocation = Location(0, 0)

    fun calculateShortestPathDistance(moves: String): Int {
        val startingPosition = Position(Direction.N, startingLocation)
        val finalPosition = parseMoves(moves).fold(startingPosition, Position::travel)

        return finalPosition.distanceFrom(startingPosition)
    }

    fun findFirstRepeatedLocation(moves: String): Location? {
        val travelRecorder = TravelRecorder(mutableListOf())
        val startingPosition = Position(Direction.N, startingLocation)
        startingPosition.enableTravelRecording(travelRecorder)
        parseMoves(moves).fold(startingPosition, Position::travel)
        logger.info { "${travelRecorder.locationsVisited.size} locations visited." }
        travelRecorder.locationsVisited.forEachIndexed { i, l -> logger.fine { "$i: $l" } }

        return travelRecorder.locationsVisited.findFirstDuplicate()
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
