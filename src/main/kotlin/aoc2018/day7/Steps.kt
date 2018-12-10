package aoc2018.day7

data class Step(val id: String, val timeToComplete: Int = 0)
data class Worker(val id: Int, var currentStep: Step? = null, var secondsSpent: Int = 0) {

    fun isWorking() = currentStep != null

    @Synchronized fun assignWork(step: Step?, seconds: Int = 0) {
        currentStep = step
        secondsSpent = seconds
    }

    @Synchronized fun isWorkComplete() = currentStep != null && secondsSpent > currentStep!!.timeToComplete

}

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

    fun calculateOrderWithWorkers(steps: List<Pair<Step, Step>>, numWorkers: Int): Pair<List<Step>, Long> {
        var currentTime: Long = 0
        val workers = (0..(numWorkers-1)).map { i -> Worker(i) }

        val stepsInOrderOfCompletion = mutableListOf<Step>()
        val remainingStepsSorted = (steps.map { it.first } + steps.map { it.second }).distinct().sortedBy { it.id }.toMutableList()
        val totalStepsCount = remainingStepsSorted.size

        val dependencies: MutableMap<Step, MutableSet<Step>> = steps.fold(mutableMapOf()) { deps, (dependentStep, step) ->
            deps.computeIfAbsent(step) { mutableSetOf() }.add(dependentStep)
            deps
        }

        while(stepsInOrderOfCompletion.size < totalStepsCount || workers.any(Worker::isWorking)) {
            // 1. Update current worker's states
            workers.filter(Worker::isWorking).forEach { worker ->
                worker.secondsSpent++
                if (worker.isWorkComplete()) {
                    val completedStep = worker.currentStep!!
                    stepsInOrderOfCompletion.add(completedStep)
                    dependencies.forEach { _, dependentSteps -> dependentSteps.remove(completedStep) }
                    worker.assignWork(null)
                }
            }

            // 2. Assign new worker to idle workers
            workers.filterNot(Worker::isWorking).forEach {
                val nextStep = remainingStepsSorted.firstOrNull { step ->
                    !dependencies.containsKey(step) || dependencies.getOrDefault(step, mutableSetOf()).isEmpty()
                }
                if (nextStep != null) {
                    remainingStepsSorted.remove(nextStep)
                    it.assignWork(nextStep, 1)
                }
            }

            report(currentTime, workers, stepsInOrderOfCompletion)
            currentTime++
        }

        return Pair(stepsInOrderOfCompletion, currentTime - 1)
    }

    private fun report(currentTime: Long, workers: List<Worker>, stepsInOrderOfCompletion: MutableList<Step>) {
        val printWorker = { w:Worker -> w.currentStep?.id ?: "." }
        println("Time: $currentTime " +
                "Workers: [${workers.map(printWorker).joinToString(" ")}] " +
                "Completed: ${stepsInOrderOfCompletion.map(Step::id).joinToString("")}")
    }

}
