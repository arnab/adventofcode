package aoc2018.day6

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ManhattanTest {

    @Test
    fun part1_LargestArea_Problem1() {
        val points = parseData(TestResourceReader.readFile("aoc2018/day6/input.txt"))
        val largestArea = Manhattan.largestArea(points)
        Assertions.assertEquals(5187, largestArea)
    }

    @Test
    fun part1_LargestArea_Example1() {
        val points = parseData("""
            1, 1
            1, 6
            8, 3
            3, 4
            5, 5
            8, 9
        """.trimIndent())
        val largestArea = Manhattan.largestArea(points)
        Assertions.assertEquals(17, largestArea)
    }

    @Test
    fun part1_SafeRegionSize_Problem1() {
        val points = parseData(TestResourceReader.readFile("aoc2018/day6/input.txt"))
        val safeRegionSize = Manhattan.safeRegionSize(points, 10000)
        Assertions.assertEquals(34829, safeRegionSize)
    }

    @Test
    fun part2_SafeRegionSize_Example1() {
        val points = parseData("""
            1, 1
            1, 6
            8, 3
            3, 4
            5, 5
            8, 9
        """.trimIndent())
        val safeRegionSize = Manhattan.safeRegionSize(points, 32)
        Assertions.assertEquals(16, safeRegionSize)
    }

    private fun parseData(data: String): List<Point> {
        return data.split("\n")
                .filterNot(String::isEmpty)
                .map { it.split(", ") }
                .mapIndexed { i, (x, y) -> Point(i+1, x.toInt(), y.toInt()) }
    }

}