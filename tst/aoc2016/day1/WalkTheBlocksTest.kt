package aoc2016.day1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class WalkTheBlocksTest {
    fun walkTheBlockWithInputData(data: String): Int {
        return WalkTheBlocks.calculateShortestPathDistance(data)
    }

    fun walkTheBlockWithInputDataFromFile(filename: String): Int {
        val input = File(ClassLoader.getSystemResource(filename).toURI()).readText()
        return walkTheBlockWithInputData(input)
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
        val distance = walkTheBlockWithInputDataFromFile("resources/aoc2016/day1/input.txt")
        assertEquals(234, distance)
    }
}
