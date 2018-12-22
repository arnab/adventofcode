package aoc2018.day12

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PotsTest {

    @Test
    fun part1_Generations_Problem() {
        val (initialState, rules)  = parseData(TestResourceReader.readFile("aoc2018/day12/input.txt"))
        val pots = Pots(initialState, rules)
        val answer = pots.sumAfterNGenerations(20)
        assertEquals(2736, answer)
    }

    @Test
    fun part1_Generations_Example() {
        val (initialState, rules)  = parseData(exampleData())
        val pots = Pots(initialState, rules)
        val answer = pots.sumAfterNGenerations(20)
        assertEquals(325, answer)
    }

    private fun exampleData() = """
            initial state: #..#.#..##......###...###

            ...## => #
            ..#.. => #
            .#... => #
            .#.#. => #
            .#.## => #
            .##.. => #
            .#### => #
            #.#.# => #
            #.### => #
            ##.#. => #
            ##.## => #
            ###.. => #
            ###.# => #
            ####. => #
        """.trimIndent()

    private fun parseData(data: String): Pair<String,Map<CharSequence, CharSequence>> {
        val lines = data.split("\n").filterNot(String::isEmpty)

        val initialState = lines.first().removePrefix("initial state: ")

        val rules: Map<CharSequence, CharSequence> = lines.drop(1).associate { line ->
            val (key, value) = line.split(" => ", limit = 2)
            key to value
        }
        return Pair(initialState, rules)
    }

}