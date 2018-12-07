package aoc2018.day7

data class Step(val id: String)

object Steps {
    fun calculateOrder(steps: List<Pair<Step, Step>>): List<Step> {
        return steps.map {it.first}
    }

}
