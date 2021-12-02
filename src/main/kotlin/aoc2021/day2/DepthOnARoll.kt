package aoc2021.day2

object DepthOnARoll {

    data class Command(val direction: String, val units: Int) {
        companion object {
            fun from(data: String): Command {
                val direction = data.split(" ")[0]
                val units = data.split(" ")[1].toInt()
                return Command(direction, units)
            }
        }

        fun execute(position: Position): Position = when(this.direction) {
            "forward" -> Position(position.distance + this.units, position.depth)
            "up" -> Position(position.distance, position.depth - this.units)
            "down" -> Position(position.distance, position.depth + this.units)
            else -> throw IllegalArgumentException("Unknown command: $this")
        }

        fun executeWithAim(position: Position): Position = when(this.direction) {
            "forward" -> Position(
                position.distance + this.units,
                position.depth + (position.aim * this.units),
                position.aim
            )

            "up" -> Position(position.distance, position.depth, position.aim - this.units)

            "down" -> Position(position.distance, position.depth, position.aim + this.units)

            else -> throw IllegalArgumentException("Unknown command: $this")
        }
    }

    data class Position(val distance: Int, val depth: Int, val aim: Int = 0)

    fun parse(data: String) = data.split("\n").map { Command.from(it) }

    fun calculate(commands: List<Command>): Int {
        val finalPosition = commands.fold(Position(0, 0)) { currentPosition, command ->
            command.execute(currentPosition)
        }

        return finalPosition.depth * finalPosition.distance
    }

    fun calculateWithAim(commands: List<Command>): Int {
        val finalPosition = commands.fold(Position(0, 0, 0)) { currentPosition, command ->
            command.executeWithAim(currentPosition)
        }

        return finalPosition.depth * finalPosition.distance
    }
}
