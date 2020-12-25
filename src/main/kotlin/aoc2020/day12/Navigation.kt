package aoc2020.day12

import aoc.extensions.possiblyNegativeLookup
import aoc.extensions.splitAtIndex

object Navigation {
    fun parse(rawData: String) = rawData.split("\n").map { Turn.from(it) }

    fun navigate(turns: List<Turn>, shipAtStart: Ship = Ship()) = turns.fold(shipAtStart) { ship, turn ->
        val shipAfterTurn = ship.navigate(turn)
        println("After processing $turn, ship is: $shipAfterTurn")
        shipAfterTurn
    }

    enum class Direction {
        NORTH, SOUTH, EAST, WEST,
        RIGHT, LEFT, FORWARD;

        private val order by lazy { listOf(NORTH, EAST, SOUTH, WEST) }

        fun nextDirection(turnByDegrees: Int): Direction {
            val currentIndex = order.indexOf(this)
            val nextIndex = currentIndex + turnByDegrees / 90
            return order.possiblyNegativeLookup(nextIndex)
        }
    }

    data class Turn(
        val direction: Direction,
        val amount: Int
    ) {
        companion object {
            fun from(str: String) = str.splitAtIndex(1).let { (first, rest) ->
                val direction = Direction.values().first { it.toString().startsWith(first) }
                Turn(direction, amount = rest.toInt())
            }
        }
    }

    data class Ship(
        val currentDirection: Direction = Direction.EAST,
        val currentY: Int = 0,
        val currentX: Int = 0
    ) {
        fun navigate(turn: Turn): Ship = when(turn.direction) {
            Direction.NORTH -> this.copy(
                currentY = currentY + turn.amount
            )

            Direction.SOUTH -> this.copy(
                currentY = currentY - turn.amount
            )

            Direction.EAST -> this.copy(
                currentX = currentX + turn.amount
            )

            Direction.WEST -> this.copy(
                currentX = currentX - turn.amount
            )

            Direction.RIGHT -> this.copy(
                currentDirection = currentDirection.nextDirection(turn.amount)
            )

            Direction.LEFT -> this.copy(
                currentDirection = currentDirection.nextDirection(-1 * turn.amount)
            )

            Direction.FORWARD -> navigate(turn.copy(direction = this.currentDirection))
        }
    }
}
