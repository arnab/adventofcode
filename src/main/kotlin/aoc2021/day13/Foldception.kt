package aoc2021.day13

object Foldception {

    data class Loc(val x: Int, val y: Int)

    enum class FoldDirection() { X, Y, }
    data class Fold(val dir: FoldDirection, val amount: Int)

    fun parse(data: String): Pair<List<Loc>, List<Fold>> {
        val (locationLines, foldLines) = data.split("\n\n").take(2)

        val locations = locationLines.split("\n").map { line ->
            val (x, y) = line.split(",").take(2).map { it.toInt() }
            Loc(x, y)
        }

        val folds = foldLines.split("\n")
            .map { it.replace("fold along ", "") }
            .map {
                val (dir, amount) = it.split("=").take(2)
                when( dir.toLowerCase() ) {
                    "y" -> Fold(FoldDirection.Y, amount.toInt())
                    "x" -> Fold(FoldDirection.X, amount.toInt())
                    else -> throw IllegalArgumentException("Don't know direction for fold: $it")
                }
            }

        return Pair(locations, folds)
    }

    fun fold(initLocations: List<Loc>, folds: List<Fold>, debug: Boolean = false): List<Loc> {
        folds.fold(initLocations) { locations, fold ->
            printLocations(debug, locations, fold)
//            when (fold.dir) {
//                FoldDirection.Y -> locations.keys
//            }
            emptyList()
        }

        return emptyList()
    }

    private fun printLocations(debug: Boolean, locations: List<Loc>, fold: Fold) {
        if (!debug) return

        val maxX = locations.map { it.x }.maxOf { it }
        val maxY = locations.map { it.y }.maxOf { it }
        println("=========================================================================================")
        (0..maxY).forEach { j ->
            println()
            (0..maxX).forEach { i ->
                val marker = if (locations.contains(Loc(i, j))) "#" else "."
                print(marker)
            }
        }
        println()
        println("Applying: $fold")
        println("=========================================================================================")
    }

    fun countMarkedSpots(locations: List<Loc>) = locations.size
}
