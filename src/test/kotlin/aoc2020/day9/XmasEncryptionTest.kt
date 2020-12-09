package aoc2020.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class XmasEncryptionTest {

    private val exampleInput = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day9/input.txt").readText()

    @Test
    fun `part 1 test`() {
        val data = XmasEncryption.parse(exampleInput)
        Assertions.assertEquals(127, XmasEncryption.findFirstInvalid(data, 5))
    }

    @Test
    fun `part 1 real`() {
        val data = XmasEncryption.parse(problemInput)
        Assertions.assertEquals(105950735, XmasEncryption.findFirstInvalid(data))
    }

}