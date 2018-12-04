package aoc2018.day4

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

}
