package aoc2018.day1

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ChronalCalibrationTest {

    @Test fun firstDuplicateFrequency_Problem2() {
        val data = TestResourceReader.readFile("resources/aoc2018/day1/input.txt")
            .split("\n")
            .map { it.toInt() }
//        val List<Int> frequencies = ChronalCalibration.calculateFrequencies(data)
//        Assertions.assertEquals(278221, frequencies)
    }

}