package aoc2018.day7

data class Step(val id: String)

object Steps {

    fun calculateOrder(steps: List<Pair<Step, Step>>): List<Step> {
        val stepsInOrderOfCompletion = mutableListOf<Step>()
        val remainingStepsSorted = (steps.map { it.first } + steps.map { it.second }).distinct().sortedBy { it.id }.toMutableList()
        val totalStepsCount = remainingStepsSorted.size

        val dependencies: MutableMap<Step, MutableSet<Step>> = steps.fold(mutableMapOf()) { deps, (dependentStep, step) ->
            deps.computeIfAbsent(step) { mutableSetOf() }.add(dependentStep)
            deps
        }

        while(stepsInOrderOfCompletion.size < totalStepsCount) {
            val nextStep = remainingStepsSorted.first { step ->
                !dependencies.containsKey(step) || dependencies.getOrDefault(step, mutableSetOf()).isEmpty()
            }
            stepsInOrderOfCompletion.add(nextStep)
            remainingStepsSorted.remove(nextStep)
            dependencies.forEach { _, dependentSteps -> dependentSteps.remove(nextStep) }
        }

        return stepsInOrderOfCompletion
    }

}
