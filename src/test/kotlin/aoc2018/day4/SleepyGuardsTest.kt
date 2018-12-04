package aoc2018.day4

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SleepyGuardsTest {

    @Test
    fun part1_mostSleepyGuard_Problem1() {
        val guardSchedules = parseData(TestResourceReader.readFile("aoc2018/day4/input.txt"))

        Assertions.assertEquals(4, 2)
    }

    @Test
    fun part1_mostSleepyGuard_Example1() {
        val guardSchedules = parseData("""
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-01 23:58] Guard #99 begins shift
            [1518-11-02 00:40] falls asleep
            [1518-11-02 00:50] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
            [1518-11-04 00:02] Guard #99 begins shift
            [1518-11-04 00:36] falls asleep
            [1518-11-04 00:46] wakes up
            [1518-11-05 00:03] Guard #99 begins shift
            [1518-11-05 00:45] falls asleep
            [1518-11-05 00:55] wakes up
        """.trimIndent())

        Assertions.assertEquals(4, 2)
    }

    private fun parseData(data: String): Map<Int,List<GuardRecord>> {
        val rawDataLines = data.split("\n")
                .sorted()
                .filterNot(String::isEmpty)
        return SleepyGuards.recordGuardSchedules(rawDataLines)
    }

}
