package aoc2018.day3

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FabricClaimCalculatorTest {

    // Matches: #1382 @ 790,566: 12x15
    private val dataLineRegex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

    @Test
    fun conflictingSquares_Problem1() {
        val claims = parseData(TestResourceReader.readFile("resources/aoc2018/day3/input.txt"))
//        println("Claims data: $claims")

        val conflictingSquares = FabricClaimCalculator.findConflictingSquares(claims)
//        println("Conflicting Squares: $conflictingSquares")

        Assertions.assertEquals(0, conflictingSquares.size)
    }

    @Test
    fun conflictingSquares_Example1() {
        val claims = parseData(
            """
                #1 @ 1,3: 4x4
                #2 @ 3,1: 4x4
                #3 @ 5,5: 2x2
                """.trimIndent())
        println("Claims data: $claims")

        val conflictingSquares = FabricClaimCalculator.findConflictingSquares(claims)
        println("Conflicting Squares: $conflictingSquares")

        Assertions.assertEquals(4, conflictingSquares.size)
    }

    @Test
    fun nonConflictingClaims_Problem1() {
        val claims = parseData(TestResourceReader.readFile("resources/aoc2018/day3/input.txt"))

        val nonConflictingClaims = FabricClaimCalculator.findNonConflictingClaims(claims)
        println("Non Conflicting Claims: $nonConflictingClaims")

        Assertions.assertEquals(1, nonConflictingClaims.size)
        Assertions.assertEquals(3, nonConflictingClaims.first().id)
    }

    @Test
    fun nonConflictingClaims_Example1() {
        val claims = parseData(
            """
                #1 @ 1,3: 4x4
                #2 @ 3,1: 4x4
                #3 @ 5,5: 2x2
                """.trimIndent())

        val nonConflictingClaims = FabricClaimCalculator.findNonConflictingClaims(claims)
        println("Non Conflicting Claims: $nonConflictingClaims")

        Assertions.assertEquals(1, nonConflictingClaims.size)
        Assertions.assertEquals(3, nonConflictingClaims.first().id)
    }

    private fun parseDataLine(dataLine: String): Claim {
        val matchResult = dataLineRegex.find(dataLine)
        val (id, left, top, width, height) = matchResult!!.destructured
        return Claim(id.toInt(), left.toInt(), top.toInt(), width.toInt(), height.toInt())
    }

    private fun parseData(data: String): List<Claim> {
        return data.split("\n")
            .filterNot(String::isEmpty)
            .map { parseDataLine(it) }

    }


}