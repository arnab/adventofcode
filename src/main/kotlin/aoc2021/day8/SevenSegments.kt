package aoc2021.day8

object SevenSegments {
    data class Observation(
        val pattern: List<String>,
        val output: List<String>
    )

    fun parse(data: String) = data.split("\n").map { line ->
        val (pattern, output) = line.split(" | ")
        Observation(
            pattern.split(" "),
            output.split(" "),
        )
    }

    fun countDigitsWithUniqSegments(observations: List<Observation>): Int {
        return observations.flatMap { it.output }.count {
            it.length == 2 || // -> 1
                it.length == 4 || // -> 4
                it.length == 3 || // -> 7
                it.length == 7 // -> 8
        }

    }
}
