package aoc2018.day4

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import java.time.LocalDate



data class SleepDuration(val start: LocalDateTime, var end: LocalDateTime?)

object SleepyGuards {

    // Matches: [1518-05-03 00:02] Guard #3469 begins shift
    private val dataLineRegex = """\[([\d\- :].*)\] (.*)""".toRegex()

    private val guardIdRegex = """Guard #(\d+) begins shift""".toRegex()

    fun recordGuardSchedules(data: List<String>): Map<Int, List<SleepDuration>> {
        val guardRecords = mutableMapOf<Int, MutableList<SleepDuration>>()
        var lastSeenGuardId :Int? = null

        data.forEach { dataLine ->
            val (timestampStr, action) = dataLineRegex.find(dataLine)!!.destructured
            val timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("y-M-d H:m"))

            when {
                action.contains("begins shift") -> {
                    val (guardIdStr) = guardIdRegex.find(action)!!.destructured
                    lastSeenGuardId = guardIdStr.toInt()
                    guardRecords.putIfAbsent(lastSeenGuardId!!, mutableListOf())
                }

                action.contains("falls asleep") -> {
                    guardRecords[lastSeenGuardId]!!.add(SleepDuration(timestamp, null))
                }

                action.contains("wakes up") -> {
                    guardRecords[lastSeenGuardId]!!.last().end = timestamp.minusMinutes(1)
                }
            }
        }

        return guardRecords
    }

    fun mostSleepyGuard(guards: Map<Int, List<SleepDuration>>): Pair<Int, List<SleepDuration>> {
        return guards.maxBy { totalSleepDuration(it.value) }!!
                .toPair()
    }

    fun totalSleepDuration(sleepDurations: List<SleepDuration>): Long {
        return sleepDurations.map { Duration.between(it.start, it.end!!.plusMinutes(1)).toMinutes() }.sum()
    }

    fun mostSleepyMinute(sleepDurations: List<SleepDuration>): Int {
        val allMinutes = mutableListOf<Int>()

        sleepDurations.forEach { sleepDuration ->
            var start = sleepDuration.start
            val end = sleepDuration.end
            do {
                allMinutes.add(start.minute)
                start = start.plus(Duration.ofMinutes(1))
            } while (start <= end)
        }

        return allMinutes.groupingBy { it }.eachCount().maxBy { it.value }!!.key
    }

}
