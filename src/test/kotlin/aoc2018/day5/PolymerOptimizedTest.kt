package aoc2018.day5

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PolymerOptimizedTest {

    @Test
    fun part1_reducePolymer_Problem1() {
        val reducedPolymerSize = PolymerOptimized.reduce(TestResourceReader.readFile("aoc2018/day5/input.txt"))
        Assertions.assertEquals(9462, reducedPolymerSize)
    }

    @Test
    fun part1_reducePolymer_Example1() {
        val reducedPolymerSize = PolymerOptimized.reduce("dabAcCaCBAcCcaDA")
        Assertions.assertEquals(10, reducedPolymerSize)
    }

    @Test
    fun part2_reducePolymer_Problem1() {
        val (type, reducedPolymerSize) = PolymerOptimized.reduceByRemovingTypes(TestResourceReader.readFile("aoc2018/day5/input.txt"))
        Assertions.assertEquals('m', type)
        Assertions.assertEquals(4952, reducedPolymerSize)
    }

    @Test
    fun part2_reducePolymer_Example1() {
        val (type, reducedPolymerSize) = PolymerOptimized.reduceByRemovingTypes("dabAcCaCBAcCcaDA")
        Assertions.assertEquals('c', type)
        Assertions.assertEquals(4, reducedPolymerSize)
    }

}
