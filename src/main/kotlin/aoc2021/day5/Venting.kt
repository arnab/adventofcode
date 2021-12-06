package aoc2021.day5

object Venting {
    data class Point(val x: Int, val y: Int) {
        companion object {
            fun from(data: String): Point {
                val x = data.split(",").first().toInt()
                val y = data.split(",").last().toInt()
                return Point(x, y)
            }
        }
    }

    fun parse(data: String): List<Pair<Point, Point>> = data.split("\n")
        .map {
            val left = Point.from(it.split(" -> ").first())
            val right = Point.from(it.split(" -> ").last())
            Pair(left, right)
        }

    fun countDangerousPoints(lines: List<Pair<Point, Point>>): Int {
        val points = lines.fold(mutableMapOf<Point, Int>()) { counts, (start, end) ->
            if (start.x == end.x) {
                IntRange.from(start.y, end.y).forEach { y ->
                    val point = Point(start.x, y)
                    counts[point] = counts.getOrDefault(point, 0) + 1
                }
            }

            if (start.y == end.y) {
                IntRange.from(start.x, end.x).forEach { x ->
                    val point = Point(x, start.y)
                    counts[point] = counts.getOrDefault(point, 0) + 1
                }
            }

            counts
        }

        return points.values.count { it >= 2 }
    }
}

private fun IntRange.Companion.from(a: Int, b: Int): IntRange = listOf(a, b).sorted().let { nums ->
    nums.first()..nums.last()
}
