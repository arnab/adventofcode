package aoc2016.day7

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7DoItTest {
    @Test fun supportsTls_1() {
        val data = "aba[bab]xyz"
        assertEquals(true, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_2() {
        val data = "xyx[xyx]xyx"
        assertEquals(false, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_3() {
        val data = "aaa[kek]eke"
        assertEquals(true, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_4() {
        val data = "aaa[keke]eke"
        assertEquals(false, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_5() {
        val data = "aaa[kek]ekek"
        assertEquals(true, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_6() {
        val data = "zazbz[bzb]cdb"
        assertEquals(true, Day7DoIt.supportsTls(data))
    }

    @Test fun supportsTls_7() {
        val data = "shkjoyjuiufvxbluji[saofjqdwpwodltmra]xldohzmyameybbnw[zwaispolnanumhtz]hpobrxhytzqmkrkf"
        assertEquals(false, Day7DoIt.supportsTls(data))
    }

    @Test fun doit_Example1() {
        val data = """
                   aba[bab]xyz
                   xyx[xyx]xyx
                   aaa[kek]eke
                   zazbz[bzb]cdb

                   """.trimIndent()
        val res = Day7DoIt.doit(data)
        assertEquals(3, res)
    }

    @Test fun doit_Problem() {
        val data = TestResourceReader.readFile("aoc2016/day7/input.txt")
        val res = Day7DoIt.doit(data)
        assertEquals(242, res)
    }

}
