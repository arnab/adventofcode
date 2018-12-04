package aoc2016.day7

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class IPAddressesTest {
    @Test fun IpAddress_supportSsl_1() {
        assertEquals(true, IpAddress("aba[bab]xyz").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_2() {
        assertEquals(false, IpAddress("xyx[xyx]xyx").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_3() {
        assertEquals(true, IpAddress("aaa[kek]eke").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_4() {
        assertEquals(true, IpAddress("aaa[keke]eke").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_5() {
        assertEquals(true, IpAddress("aaa[kek]ekek").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_6() {
        assertEquals(true, IpAddress("zazbz[bzb]cdb").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_7() {
        assertEquals(false, IpAddress("shkjoyjuiufvxbluji[saofjqdwpwodltmra]xldohzmyameybbnw[zwaispolnanumhtz]hpobrxhytzqmkrkf").supportsSsl())
    }

    @Test fun IpAddress_supportSsl_8() {
        assertEquals(true, IpAddress("zazbz[bzb]cdbaaa[keke]eke[]aba").supportsSsl())
    }

    @Test fun supportsSsl_Example() {
        val data = """
                   aba[bab]xyz
                   xyx[xyx]xyx
                   aaa[kek]eke
                   zazbz[bzb]cdb
                   zazbz[bzb]cdbaaa[keke]eke
                   """.trimIndent()
        assertEquals(4, IPAddresses.supportsSsl(data).count())
    }

    @Test fun supportsSsl_Problem() {
        val data = TestResourceReader.readFile("resources/aoc2016/day7/input.txt")
        assertEquals(1, IPAddresses.supportsSsl(data).count())
    }
}
