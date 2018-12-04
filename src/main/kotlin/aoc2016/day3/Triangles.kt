package aoc2016.day3


data class Triangle(val a: Int, val b: Int, val c: Int) {
    fun  isValid(): Boolean = (a + b > c) && (b + c > a) && (c + a > b)
}

object Triangles {
    fun  countValid(data: String): Int {
        return data.lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .map { it.split(Regex("""\s+""")) }
                .map { line ->
                    val (a, b, c) = line
                    Triangle(a.toInt(), b.toInt(), c.toInt())
                }
                .count { it.isValid() }
    }

    fun countValidVertical(data: String): Int {
        return data.lines()
                .map(String::trim)
                .filter(String::isNotBlank)
                .map { it.split(Regex("""\s+""")) }
                .withIndex()
                .groupBy {
                    val (index, _) = it
                    index / 3
                }
                .values
                .toList()
                .map { it.map { it.value } }
                .map(this::trianglesFromGroupsOfThree)
                .flatten()
                .count { it.isValid() }
    }

    private fun trianglesFromGroupsOfThree(group: List<List<String>>): List<Triangle> {
        return listOf(trianglesFromGroups(group, 0), trianglesFromGroups(group, 1), trianglesFromGroups(group, 2))
                .flatten()
    }

    private fun trianglesFromGroups(group: List<List<String>>, i: Int): List<Triangle> {
        return listOf(
                Triangle(group[0][i].toInt(), group[1][i].toInt(), group[2][i].toInt())
        )
    }
}
