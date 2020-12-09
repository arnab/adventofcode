package aoc2020.day8

import java.lang.IllegalArgumentException

object InfiniteLoop {

    data class Program(
        val instructions: List<Pair<String, Int>>,
        val currentState: State
    ) {
        companion object {
            fun parse(rawInstructions: String): Program =
                rawInstructions.split("\n")
                    .map { it.parseInstruction() }
                    .let { instructions -> Program(instructions, State.init()) }
        }

        data class State(
            val currentInstruction: Int,
            val currentAccumulatedValue: Int,
            val seenInstructions: Set<Int>
        ) {
            companion object {
                fun init() = State(0,0, emptySet())
            }
        }

        fun eval(): State = evalSafely(currentState)

        private fun evalSafely(state: State): State {
            val allInstructionsProcessed = state.currentInstruction >= this.instructions.size
            val instructionAlreadyEvaluated = state.seenInstructions.contains(state.currentInstruction)
            if (allInstructionsProcessed || instructionAlreadyEvaluated) return state

            val nextState = evalInstruction(this.instructions[state.currentInstruction], state)
            return evalSafely(nextState)
        }

        fun detectAndRepair(): State? {
            generateAllPossibleInstructions(this.instructions).forEach { instructions ->
                val program = Program(instructions, State.init())

                try {
                    // If we successfully execute the program, we are done!
                    return program.evalAndAbortOnLoops(program.currentState)
                } catch (e: IllegalArgumentException) {
                    println("Dang... Still failing... Let's try the next set of tweaked instruction")
                }
            }

            return null
        }

        private fun generateAllPossibleInstructions(
            instructions: List<Pair<String, Int>>
        ): Sequence<List<Pair<String, Int>>> {
            val allInstructionSets = mutableListOf<List<Pair<String, Int>>>()
            for (i in instructions.indices) {
                val (operator, value) = instructions[i]
                if (operator in listOf("nop", "jmp")) {
                    val newOperator = if (operator == "nop") "jmp" else "nop"
                    instructions.toMutableList()
                        .apply { this[i] = Pair(newOperator, value) }
                        .also { allInstructionSets.add(it) }
                }
            }
            return allInstructionSets.asSequence()
        }

        private fun evalAndAbortOnLoops(state: State): State {
            val instructionAlreadyEvaluated = state.seenInstructions.contains(state.currentInstruction)
            if (instructionAlreadyEvaluated) {
                throw IllegalArgumentException("Instr #${state.currentInstruction} already evaluated: ${state.seenInstructions}")
            }

            val allInstructionsProcessed = state.currentInstruction >= this.instructions.size
            if (allInstructionsProcessed) return state

            val nextState = evalInstruction(this.instructions[state.currentInstruction], state)
            return evalAndAbortOnLoops(nextState)
        }

        private fun evalInstruction(instruction: Pair<String, Int>, state: State): State = instruction.let {
            (operator, value) ->
            when(operator) {
                "nop" -> state.copy(
                    seenInstructions = state.seenInstructions + state.currentInstruction,
                    currentInstruction = state.currentInstruction + 1
                )

                "acc" -> state.copy(
                    seenInstructions = state.seenInstructions + state.currentInstruction,
                    currentInstruction = state.currentInstruction + 1,
                    currentAccumulatedValue = state.currentAccumulatedValue + value
                )

                "jmp" -> state.copy(
                    seenInstructions = state.seenInstructions + state.currentInstruction,
                    currentInstruction = state.currentInstruction + value
                )

                else -> throw IllegalArgumentException("Unknown operator: $operator")
            }
        }
    }
}

private fun String.parseInstruction() = this.split(" ").let {
    (operation, value) -> Pair(operation, value.toInt())
}

