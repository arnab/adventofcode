package aoc2018.day4

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class ActivityType(val type: String) {
    AWAKE("awake"),
    ASLEEP("asleep")
}

data class GuardRecord(val id: Int, val activities: List<Activity>)
data class Activity(val type: ActivityType, val start: LocalDateTime, val end: LocalDateTime?)

object SleepyGuards {

    val guardSchedules = mutableMapOf<Int, List<GuardRecord>>()

    // Matches: [1518-05-03 00:02] Guard #3469 begins shift
    private val dataLineRegex = """\[([\d\- :].*)\] (.*)""".toRegex()

    private val guardIdRegex = """Guard #(\d+) begins shift""".toRegex()

    fun recordGuardSchedules(data: List<String>): Map<Int, List<GuardRecord>> {
        var currentGuardId :Int? = null

        data.forEach { dataLine ->
            val (timestampStr, action) = dataLineRegex.find(dataLine)!!.destructured
            val timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("y-M-d H:m"))

            when {
                action.contains("begins shift") -> {
                    val (guardIdStr) = guardIdRegex.find(action)!!.destructured
                    val guardId = guardIdStr.toInt()

                    if (currentGuardId != null && currentGuardId != guardId) {
                        TODO("Complete the previous guard's records")
                    }
                    currentGuardId = guardId

                    var activity: Activity? = null
                    if (guardSchedules.containsKey(guardId)) {
                        activity = guardSchedules[guardId]?.last()?.activities?.last()
                    } else {
//                        guardSchedules.
                    }

                }

                action.contains("falls asleep") -> {
                    println("time: $timestamp")
                }

                action.contains("wakes up") -> {
                    println("time: $timestamp")
                }
            }

        }

        return guardSchedules
    }

}
