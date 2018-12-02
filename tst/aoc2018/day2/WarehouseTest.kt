package aoc2018.day2

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WarehouseTest {

    @Test
    fun calculateCheckSum_Problem1() {
        val data = TestResourceReader.readFile("resources/aoc2018/day2/input.txt")
            .split("\n")
            .map { it.split("").filterNot { c -> c.isEmpty() } }

        val countsByLetterPerWord = Warehouse.countsByLetterPerWord(data)

//        Assertions.assertEquals(0, Warehouse.calculateChecksum(countsByLetterPerWord))
    }

    @Test
    fun calculateCheckSum_Problem1_Example1() {
        val data = """
            abcdef
            bababc
            abbcde
            abcccd
            aabcdd
            abcdee
            ababab
        """.trimIndent()
            .split("\n")
            .map { it.split("").filterNot { c -> c.isEmpty() } }

        val countsByLetterPerWord = Warehouse.countsByLetterPerWord(data)

//        Assertions.assertEquals(0, Warehouse.calculateChecksum(countsByLetterPerWord))
    }

}