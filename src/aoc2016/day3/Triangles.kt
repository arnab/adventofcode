package aoc2016.day3


data class Triangle(val a: Int, val b: Int, val c: Int) {
    fun  isValid(): Boolean {
        return true
    }

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

}
