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

        fun eval(): State = evalRecursively(currentState)

        private fun evalRecursively(state: State): State {
            val allInstructionsProcessed = state.currentInstruction >= this.instructions.size
            val instructionAlreadyEvaluated = state.seenInstructions.contains(state.currentInstruction)
            if (allInstructionsProcessed || instructionAlreadyEvaluated) return state

            val nextState = evalInstruction(this.instructions[state.currentInstruction], state)
            return evalRecursively(nextState)
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

