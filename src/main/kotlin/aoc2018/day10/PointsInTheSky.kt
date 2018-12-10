package aoc2018.day10

data class Point(val x: Int, val y: Int, val vX: Int, val vY: Int) {

    companion object {

        // Matches "position=< 9,  1> velocity=< 0,  2>"
        private val dataLineRegex = """position=<\s*(-?\d+),\s*(-?\d+)> velocity=<\s*(-?\d+),\s*(-?\d+)>""".toRegex()

        fun from(data: String): Point {
            val (x, y, vX, vY) = dataLineRegex.find(data)!!.destructured
            return Point(x.toInt(), y.toInt(), vX.toInt(), vY.toInt())
        }

    }

    fun atTime(t: Int) = Point(x + vX * t, y + vY * t, vX, vY)

}

object PointsInTheSky {

    fun findTimeForMostCompactPositons(points: List<Point>, maxTime: Int = 20_000): List<Pair<Int, Int>> {
        return (0..maxTime).map { t ->
            val pointsAtTime = points.map { it.atTime(t) }
            val minX = pointsAtTime.minBy { it.x }!!.x
            val maxX = pointsAtTime.maxBy { it.x }!!.x
            val minY = pointsAtTime.minBy { it.y }!!.y
            val maxY = pointsAtTime.maxBy { it.y }!!.y
            val spread = maxX - minX + maxY - minY
            Pair(t, spread)
        }.sortedBy { it.second }.take(3)
    }

    fun printPositions(points: List<Point>, time: Int) {
        val pointsAtTime = points.map { it.atTime(time) }
        display(time, pointsAtTime, displayFull = true)
    }

    private fun display(t: Int, pointsAtTime: List<Point>, displayFull: Boolean = false) {
        val minX = pointsAtTime.minBy { it.x }!!.x
        val maxX = pointsAtTime.maxBy { it.x }!!.x
        val minY = pointsAtTime.minBy { it.y }!!.y
        val maxY = pointsAtTime.maxBy { it.y }!!.y
        val spread = maxX - minX + maxY - minY
        println("Time: $t, Spread: $spread")

        if (displayFull) {
            (minY..maxY).forEach { j ->
                (minX..maxX).forEach { i ->
                    val pointFound: Point? = pointsAtTime.find { it.x == i && it.y == j }
                    print(if (pointFound == null) " " else "X")
                }
                println("")
            }
        }
    }

}