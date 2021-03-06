package aoc2018.day5

import aoc.util.TestResourceReader
import org.junit.Ignore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PolymerTest {

    @Ignore // Takes too long, use PolymerOptimizedTests
    @Test
    fun part1_reducePolymer_Problem1() {
//        val rawPolymers = parseData(TestResourceReader.readFile("aoc2018/day5/input.txt"))
//        val reducedPolymer = Polymer.reduce(rawPolymers)
//        val reducedPolymerStr = reducedPolymer.map(PolymerUnit::type).joinToString("")
//        Assertions.assertEquals(9462, reducedPolymerStr.length)
    }

    @Test
    fun part1_reducePolymer_Example1() {
        val rawPolymers = parseData("dabAcCaCBAcCcaDA")
        val reducedPolymer = Polymer.reduce(rawPolymers, true)
        val reducedPolymerStr = reducedPolymer.map(PolymerUnit::type).joinToString("")
        Assertions.assertEquals("dabCBAcaDA", reducedPolymerStr)
    }

    @Test
    fun part2_reducePolymer_Problem1() {
        val rawPolymers = parseData(TestResourceReader.readFile("aoc2018/day5/input.txt"))
//        // takes multiple minutes to complete, given the data size
//        val (type, reducedPolymer) = Polymer.reduceByRemovingBadUnits(rawPolymers, true)
//        val reducedPolymerStr = reducedPolymer.map(PolymerUnit::type).joinToString("")
//        println("type: $type, reduced polymer length: ${reducedPolymerStr.length}")
//        Assertions.assertEquals("m", type.toString())
//        Assertions.assertEquals(4952, reducedPolymerStr.length)
    }

    @Test
    fun part2_reducePolymer_Example1() {
        val rawPolymers = parseData("dabAcCaCBAcCcaDA")
        val (type, reducedPolymer) = Polymer.reduceByRemovingBadUnits(rawPolymers, true)
        val reducedPolymerStr = reducedPolymer.map(PolymerUnit::type).joinToString("")
        Assertions.assertEquals("c", type.toString())
        Assertions.assertEquals("daDA", reducedPolymerStr)
    }

    private fun parseData(data: String): List<PolymerUnit> {
        return data.split("")
                .filterNot(String::isEmpty)
                .map { unit -> PolymerUnit(unit.single()) }
    }

}
