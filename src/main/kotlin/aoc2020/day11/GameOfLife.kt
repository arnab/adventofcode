package aoc2020.day11

object GameOfLife {

    private const val DEBUG_REPORT_MODE = false

    fun parse(rawData: String): List<List<Cell>> = rawData
        .split("\n")
        .mapIndexed { y, row ->
            row.toCharArray().mapIndexed { x, char ->
                val state = State.values().find { it.sign == char.toString() }!!
                Cell(y = y, x = x, state = state)
            }
        }

    enum class State(val sign: String) {
        EMPTY("L"), OCCUPIED("#"), FLOOR(".")
    }

    data class Cell(val y: Int, val x: Int, val state: State) {

        /**
         * For the cell at [1,1], these are the neighbors:
         *     [
         *       [0, 0], [0, 1], [0, 2],
         *       [1, 0],         [1, 2],
         *       [2, 0], [2, 1], [2, 2],
         *     ]
         */
        fun neighbors(cells: List<List<Cell>>): List<Cell> =
            ((y - 1)..(y + 1)).map { col ->
                ((x - 1)..(x + 1)).map { row ->
                    if (row == this.x && col == this.y)
                        null
                    else
                        cells.getOrNull(col)?.getOrNull(row)
                }
            }.flatten().filterNotNull()

        fun neighborsUntilNextSeat(cells: List<List<Cell>>): List<Cell> =
            listOf(
                Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
                Pair(0, -1), Pair(0, 1),
                Pair(1, -1), Pair(1, 0), Pair(1, 1)
            ).mapNotNull { (stepY, stepX) ->
                findNearestSeatInDirection(this.y, this.x, stepY, stepX, cells)
            }

        private fun findNearestSeatInDirection(y: Int, x: Int, stepY: Int, stepX: Int, cells: List<List<Cell>>): Cell? {
            val nextY = y + stepY
            val nextX = x + stepX
            val nextCell = cells.getOrNull(nextY)?.getOrNull(nextX)

            return if (nextCell == null || nextCell.state != State.FLOOR)
                nextCell
            else
                findNearestSeatInDirection(nextY, nextX, stepY, stepX, cells)
        }


        fun nextState(neighbors: List<Cell>): State {
            if (this.state == State.FLOOR)
                return state

            if (this.state == State.EMPTY && neighbors.none { it.state == State.OCCUPIED })
                return State.OCCUPIED

            // Part 2 rules apply...
            if (this.state == State.OCCUPIED && neighbors.filter { it.state == State.OCCUPIED }.count() >= 5)
                return State.EMPTY

            return state

        }
    }

    fun simulateUntilStable(cells: List<List<Cell>>): Pair<Int, List<List<Cell>>> {
        var generation = 0
        var changedAfterNextGeneration: Boolean
        var nextCells = cells

        do {
            generation++
            val result = simulateNextGeneration(nextCells)
            changedAfterNextGeneration = result.first
            nextCells = result.second

            if (DEBUG_REPORT_MODE)
                report(generation, changedAfterNextGeneration, nextCells)
        } while (changedAfterNextGeneration)

        return Pair(generation, nextCells)
    }

    private fun simulateNextGeneration(cells: List<List<Cell>>): Pair<Boolean, List<List<Cell>>> {
        val nextCells = cells.map { col ->
            col.map { cell ->
                val neighbors = cell.neighborsUntilNextSeat(cells)
                cell.copy( state = cell.nextState(neighbors) )
            }
        }
        return Pair(cells != nextCells, nextCells)
    }

    fun countSeats(cells: List<List<Cell>>, state: State) =
        cells.flatMap { row ->
            row.map { it.state }
        }.filter { it == state }.count()

    private fun report(generation: Int, changed: Boolean, cells: List<List<Cell>>) {
        println("\n\n")
        println("=============== Gen: $generation (Changed: $changed) ===============")
        cells.forEach { row ->
            println()
            row.forEach { cell ->
                print(cell.state.sign)
            }
        }
    }

}
