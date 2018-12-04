package aoc2016.day2

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FancyLooKeyTest {
    fun decipherLooKeyForInstructions(instructions: String): String = FancyLooKey.decipher(instructions)

    fun decipherLooKeyForInstructionsFromFile(filename: String): String {
        return decipherLooKeyForInstructions(TestResourceReader.readFile(filename))
    }

    @Test
    fun decipher_Example1() {
        val instructions = """ULL
                              RRDDD
                              LURDL
                              UUUUD""".trimIndent()
        val looKey = decipherLooKeyForInstructions(instructions)
        assertEquals("2CB3", looKey)
    }

    @Test
    fun decipher_Problem1() {
        val looKey = decipherLooKeyForInstructionsFromFile("aoc2016/day2/input1.txt")
        assertEquals("B3DB8", looKey)
    }

}
