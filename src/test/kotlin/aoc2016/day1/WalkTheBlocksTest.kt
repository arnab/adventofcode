package aoc2016.day1

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WalkTheBlocksTest {
    fun walkTheBlockWithInputData(data: String): Int {
        return WalkTheBlocks.calculateShortestPathDistance(data)
    }

    fun walkTheBlockWithInputDataFromFile(filename: String): Int {
        return walkTheBlockWithInputData(TestResourceReader.readFile(filename))
    }

    @Test
    fun calculateShortestPathDistance_Example1() {
        val distance = walkTheBlockWithInputData("R2, L3")
        assertEquals(5, distance)
    }

    @Test
    fun calculateShortestPathDistance_Example2() {
        val distance = walkTheBlockWithInputData("R2, R2, R2")
        assertEquals(2, distance)
    }

    @Test
    fun calculateShortestPathDistance_Example3() {
        val distance = walkTheBlockWithInputData("R5, L5, R5, R3")
        assertEquals(12, distance)
    }

    @Test
    fun calculateShortestPathDistance_ProblemInput() {
        val distance = walkTheBlockWithInputDataFromFile("aoc2016/day1/input.txt")
        assertEquals(234, distance)
    }

    fun findFirstRepeatedLocationWithInputData(data: String): Location? {
        return WalkTheBlocks.findFirstRepeatedLocation(data)
    }

    fun findFirstRepeatedLocationWithInputDataFromFile(filename: String): Location? {
        return findFirstRepeatedLocationWithInputData(TestResourceReader.readFile(filename))
    }

    val startingLocation = Location(0, 0)

    @Test
    fun findFirstRepeatedLocation_Example1() {
        val location = findFirstRepeatedLocationWithInputData("R8, R4, R4, R8")
        val distance = location?.distanceFrom(startingLocation)
        assertEquals(4, distance)
    }

    @Test
    fun findFirstRepeatedLocation_ProblemInput() {
        val location = findFirstRepeatedLocationWithInputDataFromFile("aoc2016/day1/input.txt")
        val distance = location?.distanceFrom(startingLocation)
        assertEquals(113, distance)
    }
}
