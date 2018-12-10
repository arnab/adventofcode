package aoc2018.day10

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PointsInTheSkyTest {

    @Test
    fun part1_PrintPositions_Problem() {
        val points: List<Point>  = parseData(TestResourceReader.readFile("aoc2018/day10/input.txt"))
        val compactPositions = PointsInTheSky.findTimeForMostCompactPositons(points)
        println("Most compact positions: $compactPositions")
        PointsInTheSky.printPositions(points, 10418)
    }

    @Test
    fun part1_PrintPositions_Example() {
        val points: List<Point>  = parseData(exampleData())
        val compactPositions = PointsInTheSky.findTimeForMostCompactPositons(points)
        PointsInTheSky.printPositions(points, 3)
    }

    private fun exampleData() = """
            position=< 9,  1> velocity=< 0,  2>
            position=< 7,  0> velocity=<-1,  0>
            position=< 3, -2> velocity=<-1,  1>
            position=< 6, 10> velocity=<-2, -1>
            position=< 2, -4> velocity=< 2,  2>
            position=<-6, 10> velocity=< 2, -2>
            position=< 1,  8> velocity=< 1, -1>
            position=< 1,  7> velocity=< 1,  0>
            position=<-3, 11> velocity=< 1, -2>
            position=< 7,  6> velocity=<-1, -1>
            position=<-2,  3> velocity=< 1,  0>
            position=<-4,  3> velocity=< 2,  0>
            position=<10, -3> velocity=<-1,  1>
            position=< 5, 11> velocity=< 1, -2>
            position=< 4,  7> velocity=< 0, -1>
            position=< 8, -2> velocity=< 0,  1>
            position=<15,  0> velocity=<-2,  0>
            position=< 1,  6> velocity=< 1,  0>
            position=< 8,  9> velocity=< 0, -1>
            position=< 3,  3> velocity=<-1,  1>
            position=< 0,  5> velocity=< 0, -1>
            position=<-2,  2> velocity=< 2,  0>
            position=< 5, -2> velocity=< 1,  2>
            position=< 1,  4> velocity=< 2,  1>
            position=<-2,  7> velocity=< 2, -2>
            position=< 3,  6> velocity=<-1, -1>
            position=< 5,  0> velocity=< 1,  0>
            position=<-6,  0> velocity=< 2,  0>
            position=< 5,  9> velocity=< 1, -2>
            position=<14,  7> velocity=<-2,  0>
            position=<-3,  6> velocity=< 2, -1>
        """.trimIndent()

    private fun parseData(data: String): List<Point> {
        return data.split("\n")
                .filterNot(String::isEmpty)
                .map { Point.from(it) }
    }

}