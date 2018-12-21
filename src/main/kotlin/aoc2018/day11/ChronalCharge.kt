package aoc2018.day11

data class Grid(val sizeX: Int, val sizeY: Int, val serial: Int) {

    private val grid = Array(sizeX + 1) { IntArray(sizeY + 1) }

    fun calculateAreaWithHighestPower(sizeOfBox: Int): Pair<Point, Int>? {
        for (y in 1 until sizeY + 1 ) {
            for (x in 1 until sizeX + 1) {
                grid[y][x] = Point(x, y).calculatePowerLevel(serial)
            }
        }

        return (1 until sizeY + 1 - sizeOfBox).flatMap { y ->
            (1 until sizeX + 1 - sizeOfBox).map { x ->
                val totalPowerOfArea = calculateTotalPowerOfArea(x, y, sizeOfBox)
                Pair(Point(x, y), totalPowerOfArea)
            }
        }.maxBy { it.second }
    }

    private fun calculateTotalPowerOfArea(x: Int, y: Int, sizeOfBox: Int): Int {
        return (y until y + sizeOfBox).flatMap { yOfPointInsideBox ->
            (x until x + sizeOfBox).map { xOfPointInsideBox ->
                Point(xOfPointInsideBox, yOfPointInsideBox).calculatePowerLevel(serial)
            }
        }.sum()
    }

}

data class Point(val x: Int, val y: Int) {
    public fun calculatePowerLevel(serial: Int) = (x + 10).let { rackId ->
        ((((rackId * y) + serial) * rackId / 100) % 10) - 5
    }
}
