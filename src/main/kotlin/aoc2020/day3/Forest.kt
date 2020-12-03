package aoc2020.day3

object Forest {

    fun parse(data: String): List<List<Char>> =
        data.split("\n")
            .reversed()
            .map { it.toCharArray().toList() }

    fun walkAndCount(forest: List<List<Char>>, stepX: Int, stepY: Int) =
        walkAndCountRecursively(
            currentCount = 0,
            currentX = 0,
            currentY = forest.size -1,
            stepX, stepY, forest
        )

    private fun walkAndCountRecursively(
        currentCount: Int,
        currentX: Int,
        currentY: Int,
        stepX: Int,
        stepY: Int,
        forest: List<List<Char>>
    ): Int {
        val nextForest = if (currentX <= forest[0].size) {
            forest
        } else {
            // expand forest, we are out of the known boundary
            forest.map { it + it }
        }

        val isCurrentSpot = nextForest[currentY][currentX]

        val nextCount = currentCount + if (isCurrentSpot.isOccupied()) 1 else 0
        val nextX = currentX + stepX
        val nextY = currentY - stepY

        if (nextY < 0) {
            return nextCount
        }

        return walkAndCountRecursively(
            currentCount = nextCount,
            currentX = nextX,
            currentY = nextY,
            stepX, stepY, nextForest
        )
    }

    private fun Char.isOccupied() = this == '#'

}
