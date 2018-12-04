package aoc2018.day1

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ChronalCalibrationTest {

    @Test fun firstDuplicateFrequency_Problem2() {
        val data = TestResourceReader.readFile("aoc2018/day1/input.txt")
            .split("\n")
            .map { it.toInt() }

        val frequencies = ChronalCalibration.calculateFrequencies(data)
        println("Frequencies seen: ${frequencies}")

        println("First duplicate frequency seen: ${ChronalCalibration.findFirstDuplicateFrequency(data)}")

        Assertions.assertEquals(413, ChronalCalibration.findFirstDuplicateFrequency(data))
    }

    @Test fun firstDuplicateFrequency_Problem2_TestData1() {
        Assertions.assertEquals(2, ChronalCalibration.findFirstDuplicateFrequency(listOf(+1, -2, +3, +1)))
    }

    @Test fun firstDuplicateFrequency_Problem2_TestData2() {
        Assertions.assertEquals(0, ChronalCalibration.findFirstDuplicateFrequency(listOf(+1, -1)))
    }

    @Test fun firstDuplicateFrequency_Problem2_TestData4() {
        Assertions.assertEquals(10, ChronalCalibration.findFirstDuplicateFrequency(listOf(+3, +3, +4, -2, -4)))
    }

    @Test fun firstDuplicateFrequency_Problem2_TestData5() {
        Assertions.assertEquals(5, ChronalCalibration.findFirstDuplicateFrequency(listOf(-6, +3, +8, +5, -6)))
    }

    @Test fun firstDuplicateFrequency_Problem2_TestData6() {
        Assertions.assertEquals(14, ChronalCalibration.findFirstDuplicateFrequency(listOf(+7, +7, -2, -7, -4)))
    }

}

