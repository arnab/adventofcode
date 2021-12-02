package aoc2021.day2

object DepthOnARoll {

    data class Command(val direction: String, val distance: Int) {
        companion object {
            fun from(data: String): Command {
                val direction = data.split(" ")[0]
                val distance = data.split(" ")[1].toInt()
                return Command(direction, distance)
            }
        }

        fun execute(position: Position): Position {
            return when(this.direction) {
                "forward" -> Position(position.distance + this.distance, position.depth)
                "up" -> Position(position.distance, position.depth - this.distance)
                "down" -> Position(position.distance, position.depth + this.distance)
                else -> throw IllegalArgumentException("Unknown command: $this")
            }
        }
    }

    data class Position(val distance: Int, val depth: Int)

    fun parse(data: String) = data.split("\n").map { Command.from(it) }

    fun calculate(commands: List<Command>): Int {
        val finalPosition = commands.fold(Position(0, 0)) { currentPosition, command ->
            command.execute(currentPosition)
        }

        return finalPosition.depth * finalPosition.distance
    }
}
