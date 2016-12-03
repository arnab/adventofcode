package aoc2016.day3

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TrianglesTest {
    fun countValidTriangles(data: String): Int = Triangles.countValid(data)
    fun countValidTrianglesFromFile(filename: String): Int = countValidTriangles(TestResourceReader.readFile(filename))
    fun countValidTrianglesVertical(data: String): Int = Triangles.countValidVertical(data)
    fun countValidTrianglesVerticalFromFile(filename: String): Int = countValidTrianglesVertical(TestResourceReader.readFile(filename))


    @Test
    fun isValid__Example1() {
        assertEquals(false, Triangle(5, 10, 25).isValid())
    }

    @Test
    fun isValid__Example2() {
        assertEquals(true, Triangle(5, 10, 13).isValid())
    }

    @Test
    fun countValid_Example1() {
        val data = """5 10 13
                      5 10 25""".trimIndent()
        val count = countValidTriangles(data)
        assertEquals(1, count)
    }

    @Test
    fun countValid_Problem1() {
        val count = countValidTrianglesFromFile("resources/aoc2016/day3/input.txt")
        assertEquals(982, count)
    }

    @Test
    fun countValidVertical_Exmaple1() {
        val data = """101 301 501
                      102 302 502
                      103 303 503
                      201 401 601
                      202 402 602
                      203 403 1784"""
        val count = countValidTrianglesVertical(data)
        assertEquals(5, count)
    }

    @Test
    fun countValidVertical_Problem1() {
        val count = countValidTrianglesVerticalFromFile("resources/aoc2016/day3/input.txt")
        assertEquals(1826, count)
    }
}
