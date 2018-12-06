package aoc2018.day6

import kotlin.math.abs

data class Point(val id: Int?, val x: Int, val y: Int) {
    fun distanceTo(other: Point): Int {
        return abs(x - other.x) + abs(y - other.y)
    }
}

object Manhattan {
    fun largestArea(points: List<Point>, debug: Boolean): Int {
        val closestPoint: MutableMap<Point, Point> = mutableMapOf()

        val maxX = points.maxBy { it.x }!!.x
        val maxY = points.maxBy { it.y }!!.y

        for (i in 0..maxX) {
            for (j in 0..maxY) {
                val currentGridPoint = Point(null, i, j)
                val closestPoints = points.map { Pair(it, it.distanceTo(currentGridPoint)) }
                        .groupBy { it.second }
                        .map { it.toPair() }
                        .minBy { it.first }
                        ?.second

                if (closestPoints?.size == 1) {
                    closestPoint[currentGridPoint] = closestPoints.first().first
                }
            }
        }

        val pointsWithMaxNeighbors = closestPoint.toList()
                .groupBy { it.second }
                .map { Pair(it.key, it.value.size) }
                .toMutableList()

        for (i in 0..maxX) {
            val j = 0
            val closestPointToExclude = closestPoint[Point(null, i, j)]
            pointsWithMaxNeighbors.removeIf { it.first == closestPointToExclude }
        }
        for (i in 0..maxX) {
            val j = maxY
            val closestPointToExclude = closestPoint[Point(null, i, j)]
            pointsWithMaxNeighbors.removeIf { it.first == closestPointToExclude }
        }
        for (j in 0..maxY) {
            val i = 0
            val closestPointToExclude = closestPoint[Point(null, i, j)]
            pointsWithMaxNeighbors.removeIf { it.first == closestPointToExclude }
        }
        for (j in 0..maxY) {
            val i = maxX
            val closestPointToExclude = closestPoint[Point(null, i, j)]
            pointsWithMaxNeighbors.removeIf { it.first == closestPointToExclude }
        }

        val pointWithMaxNeighbors = pointsWithMaxNeighbors.maxBy { it.second }

        return pointWithMaxNeighbors!!.second
    }
}