package aoc2021.day6

object Lanternfish {

    data class Fish(val counter: Int = 8) {

        fun nextDay(): List<Fish> {
            return if (counter == 0) {
                listOf(Fish(6), Fish())
            } else {
                listOf(Fish(counter - 1))
            }
        }

    }

    fun parse(data: String) = data.split(",").map { Fish(it.toInt()) }

    fun calculateSchoolSize(initialPopulation: List<Fish>, days: Int): Int {
        val schoolAtTheEnd: List<Fish> =
            (1..days).fold(initialPopulation) { school, _ ->
                school.map { fish -> listOf(fish.nextDay()) }.flatten().flatten()
            }
        return schoolAtTheEnd.size
    }
}
