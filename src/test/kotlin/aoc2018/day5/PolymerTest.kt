package aoc2018.day5

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PolymerTest {

    @Test
    fun part1_reducePolymer_Problem1() {
        val rawPolymers = parseData(TestResourceReader.readFile("aoc2018/day5/input.txt"))
        val reducedPolymer = Polymer.reduce(rawPolymers)
        val reducedPolymerStr = reducedPolymer.map(PolymerUnit::type).joinToString("")
        Assertions.assertEquals(9462, reducedPolymerStr.length)
    }

    @Test
    fun part1_reducePolymer_Example1() {
        val rawPolymers = parseData("dabAcCaCBAcCcaDA")
        val reducedPolymer = Polymer.reduce(rawPolymers, true)
        val reducedPolymerStr = reducedPolymer.map(PolymerUnit::type).joinToString("")
        Assertions.assertEquals("dabCBAcaDA", reducedPolymerStr)
    }

    private fun parseData(data: String): List<PolymerUnit> {
        return data.split("")
                .filterNot(String::isEmpty)
                .map { unit -> PolymerUnit(unit.single()) }
    }

}
