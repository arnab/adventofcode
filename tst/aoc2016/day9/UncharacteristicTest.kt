package aoc2016.day9

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UncharacteristicTest {
    @Test fun decompresses_1() {
        assertEquals("ADVENT", Uncharacteristic.decompress("ADVENT"))
    }
    @Test fun decompresses_2() {
        assertEquals("ABBBBBC", Uncharacteristic.decompress("A(1x5)BC"))
    }
    @Test fun decompresses_3() {
        assertEquals("XYZXYZXYZ", Uncharacteristic.decompress("(3x3)XYZ"))
    }
    @Test fun decompresses_4() {
        assertEquals("ABCBCDEFEFG", Uncharacteristic.decompress("A(2x2)BCD(2x2)EFG"))
    }
    @Test fun decompresses_5() {
        assertEquals("(1x3)A", Uncharacteristic.decompress("(6x1)(1x3)A"))
    }
    @Test fun decompresses_6() {
        assertEquals("X(3x3)ABC(3x3)ABCY", Uncharacteristic.decompress("X(8x2)(3x3)ABCY"))
    }

    @Test fun decompresses_Problem() {
        val data = TestResourceReader.readFile("resources/aoc2016/day9/input.txt")
        val decompressed = Uncharacteristic.decompress(data)
        assertEquals(1, decompressed.length)
    }
}
