package aoc2018.day6

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ManhattanTest {

    @Test
    fun part1_LargestArea_Problem1() {
        val coordinates = parseData(TestResourceReader.readFile("aoc2018/day6/input.txt"))
        val largestArea = Manhattan.largestArea(coordinates, true)
        Assertions.assertEquals(1, largestArea)
    }

    @Test
    fun part1_LargestArea_Example1() {
        val coordinates = parseData("""
            1, 1
            1, 6
            8, 3
            3, 4
            5, 5
            8, 9
        """.trimIndent())
        val largestArea = Manhattan.largestArea(coordinates, true)
        Assertions.assertEquals(17, largestArea)
    }

    private fun parseData(data: String): List<Coordinate> {
        return data.split("\n")
                .filterNot(String::isEmpty)
                .map { it.split(", ") }
                .map { Coordinate(it.first().toInt(), it.last().toInt()) }
    }

}