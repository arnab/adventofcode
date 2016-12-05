package aoc2016.day5

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PwdTest {
    @Test fun calculate_Example1() {
        val pwd = Pwd.calculate("abc")
        assertEquals("18f47a30", pwd)
    }

    @Test fun calculate_Problem1() {
        val pwd = Pwd.calculate("ugkcyxxp")
        assertEquals("d4cd2ee1", pwd)
    }

    @Test fun calculatePositional_Example1() {
        val pwd = Pwd.calculatePositional("abc")
        assertEquals("05ace8e3", pwd)
    }

    @Test fun calculatePositional_Problem1() {
        val pwd = Pwd.calculatePositional("ugkcyxxp")
        assertEquals("f2c730e5", pwd)
    }

    @Test fun md5() {
        assertEquals("00000155f8105dff7f56ee10fa9b9abd", Pwd.md5("abc3231929"))
        assertEquals("000008f82c5b3924a1ecbebf60344e00", Pwd.md5("abc5017308"))
        assertEquals("00000f9a2c309875e05c5a5d09f1b8c4", Pwd.md5("abc5278568"))
    }
}
