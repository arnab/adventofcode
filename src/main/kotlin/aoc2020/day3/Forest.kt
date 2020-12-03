package aoc2020.day3

object Forest {

    /**
     * Forest is organized as a 2D array where, Y -> 1st dim, X -> 2nd dim.
     * Each spot can be analyzed with [isOccupied] to see if there is a tree there.
     */
    fun parse(data: String): List<List<Char>> =
        data.split("\n")
            .reversed()
            .map { it.toCharArray().toList() }

    fun walkAndCount(forest: List<List<Char>>, stepX: Int, stepY: Int) =
        walkAndCountRecursively(
            currentTreeCount = 0,
            currentX = 0,
            currentY = forest.size -1,
            stepX, stepY, forest
        )

    private fun walkAndCountRecursively(
        currentTreeCount: Int,
        currentX: Int,
        currentY: Int,
        stepX: Int,
        stepY: Int,
        forest: List<List<Char>>
    ): Int {
        val nextGenForest = expandForestIfRequired(currentX, forest)
        val currentSpot = nextGenForest[currentY][currentX]
        val nextTreeCount = currentTreeCount + if (currentSpot.isOccupied()) 1 else 0
        val nextX = currentX + stepX
        val nextY = currentY - stepY

        if (nextY < 0) {
            // we have passed through the end/bottom of the forest
            return nextTreeCount
        }

        return walkAndCountRecursively(
            currentTreeCount = nextTreeCount,
            currentX = nextX,
            currentY = nextY,
            stepX, stepY, nextGenForest
        )
    }

    private fun Char.isOccupied() = this == '#'

    /**
     * Expand the forest, by duplicating it along the X-axis (the 2nd dim) for each Y (row), if we are out of the
     * currently known bounds of the forest (along the X-axis).
     */
    private fun expandForestIfRequired(currentX: Int, forest: List<List<Char>>) =
        if (currentX < forest[0].size) {
            forest
        } else {
            forest.map { it + it }
        }
}
