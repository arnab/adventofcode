package aoc2016.day7

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7DoItTest {
    @Test fun supportsTls_1() {
        val data = "abba[mnop]qrst"
        assertEquals(true, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_2() {
        val data = "abcd[bddb]xyyx"
        assertEquals(false, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_3() {
        val data = "aaaa[qwer]tyui"
        assertEquals(false, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_4() {
        val data = "ioxxoj[asdfgh]zxcvbn"
        assertEquals(true, Day7DoIt.supportsTls(data))
    }

    @Test fun doit_Example1() {
        val data = """
                   abba[mnop]qrst
                   abcd[bddb]xyyx
                   aaaa[qwer]tyui
                   ioxxoj[asdfgh]zxcvbn

                   """.trimIndent()
        val res = Day7DoIt.doit(data)
        assertEquals(2, res)
    }

    @Test fun doit_Problem() {
        val data = TestResourceReader.readFile("resources/aoc2016/day7/input.txt")
        val res = Day7DoIt.doit(data)
        assertEquals(0, res)
    }

}
