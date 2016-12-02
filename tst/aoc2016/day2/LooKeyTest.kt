package aoc2016.day2

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LooKeyTest {
    fun decipherLooKeyForInstructions(instructions: String): Int = LooKey.decipher(instructions)

    fun decipherLooKeyForInstructionsFromFile(filename: String): Int {
        return decipherLooKeyForInstructions(TestResourceReader.readFile(filename))
    }

    @Test
    fun decipher_Example1() {
        val instructions = """ULL
                              RRDDD
                              LURDL
                              UUUUD""".trimIndent()
        val looKey = decipherLooKeyForInstructions(instructions)
        assertEquals(1985, looKey)
    }

    @Test
    fun decipher_Problem1() {
        val looKey = decipherLooKeyForInstructionsFromFile("resources/aoc2016/day2/input1.txt")
        assertEquals(82958, looKey)
    }
}
