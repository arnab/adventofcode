package aoc2018.day4

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SleepyGuardsTest {

    @Test
    fun part1_mostSleepyGuard_Problem1() {
        val guardsWithSleepDurations = parseData(TestResourceReader.readFile("aoc2018/day4/input.txt"))

        val (guardId, sleepDurations) = SleepyGuards.mostSleepyGuard(guardsWithSleepDurations)
        val totalSleepyMinutes = SleepyGuards.totalSleepDuration(sleepDurations)
        val mostSleepyMinute = SleepyGuards.mostSleepyMinute(guardId, sleepDurations)
        val answer = guardId * mostSleepyMinute
        println("Most Sleepy: #$guardId ($totalSleepyMinutes min asleep, most on min $mostSleepyMinute). Answer: $answer!")

        Assertions.assertEquals(10, guardId)
        Assertions.assertEquals(50, totalSleepyMinutes)
        Assertions.assertEquals(24, mostSleepyMinute)
    }

    @Test
    fun part2_mostSleepyGuardOnAMinute_Problem1() {
        val guardsWithSleepDurations = parseData(TestResourceReader.readFile("aoc2018/day4/input.txt"))

        val (guardId, sleepDurations) = SleepyGuards.mostSleepyGuardOnAnyMinute(guardsWithSleepDurations)
        val mostSleepyMinute = SleepyGuards.mostSleepyMinute(guardId, sleepDurations)
        val answer = guardId * mostSleepyMinute
        println("Most Sleepy: #$guardId, on min $mostSleepyMinute. Answer: $answer!")

        Assertions.assertEquals(1657, guardId)
        Assertions.assertEquals(49, mostSleepyMinute)
    }

    @Test
    fun part1_mostSleepyGuard_Example1() {
        val guardsWithSleepDurations = parseData("""
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
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

        val (guardId, sleepDurations) = SleepyGuards.mostSleepyGuard(guardsWithSleepDurations)
        val totalSleepyMinutes = SleepyGuards.totalSleepDuration(sleepDurations)
        val mostSleepyMinute = SleepyGuards.mostSleepyMinute(guardId, sleepDurations)
        val answer = guardId * mostSleepyMinute
        println("Most Sleepy: #$guardId ($totalSleepyMinutes min asleep, most on min $mostSleepyMinute). Answer: $answer!")

        Assertions.assertEquals(10, guardId)
        Assertions.assertEquals(50, totalSleepyMinutes)
        Assertions.assertEquals(24, mostSleepyMinute)
    }

    @Test
    fun part2_mostSleepyGuardOnAMinute_Example1() {
        val guardsWithSleepDurations = parseData("""
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
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

        val (guardId, sleepDurations) = SleepyGuards.mostSleepyGuardOnAnyMinute(guardsWithSleepDurations)
        val mostSleepyMinute = SleepyGuards.mostSleepyMinute(guardId, sleepDurations)
        val answer = guardId * mostSleepyMinute
        println("Most Sleepy: #$guardId, on min $mostSleepyMinute. Answer: $answer!")

        Assertions.assertEquals(99, guardId)
        Assertions.assertEquals(45, mostSleepyMinute)
    }

    private fun parseData(data: String): Map<Int, List<SleepDuration>> {
        val rawDataLines = data.split("\n")
                .sorted()
                .filterNot(String::isEmpty)
        return SleepyGuards.recordGuardSchedules(rawDataLines)
    }

}
