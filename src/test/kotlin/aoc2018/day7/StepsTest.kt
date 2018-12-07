package aoc2018.day7

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class StepsTest {

    @Test
    fun part1_StepOrder_Problem1() {
        val steps = parseData(TestResourceReader.readFile("aoc2018/day7/input.txt"))
        val stepsInOrder = Steps.calculateOrder(steps)
        Assertions.assertEquals("", stepsInOrder.map(Step::id).joinToString(""))
    }

    @Test
    fun part1_StepOrder_Example1() {
        val steps = parseData("""
            Step C must be finished before step A can begin.
            Step C must be finished before step F can begin.
            Step A must be finished before step B can begin.
            Step A must be finished before step D can begin.
            Step B must be finished before step E can begin.
            Step D must be finished before step E can begin.
            Step F must be finished before step E can begin.
        """.trimIndent())
        val stepsInOrder = Steps.calculateOrder(steps)
        Assertions.assertEquals("CABDFE", stepsInOrder.map(Step::id).joinToString(""))
    }

    private fun parseData(data: String): List<Pair<Step, Step>> {
        return data.split("\n")
                .filterNot(String::isEmpty)
                .map { parseDataLine(it) }
    }

    // Matches: Step D must be finished before step E can begin.
    private val dataLineRegex = """Step (\w) must be finished before step (\w) can begin.""".toRegex()

    private fun parseDataLine(line: String): Pair<Step, Step> {
        val (stepOne, stepTwo)  = dataLineRegex.find(line)!!.destructured
        return Pair(Step(stepOne), Step(stepTwo))
    }

}
