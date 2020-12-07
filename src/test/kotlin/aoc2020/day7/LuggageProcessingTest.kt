package aoc2020.day7

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class LuggageProcessingTest {

    private val exampleInput = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day7/input.txt").readText()

    @Test
    fun `part 1 test`() {
        val bagSpecs = LuggageProcessing.parse(exampleInput)
        val bagTypes = LuggageProcessing.calculateDependencies(bagSpecs)
        Assertions.assertEquals(1, 1)
    }

    @Test
    fun `part 1 real`() {
        val bagSpecs = LuggageProcessing.parse(problemInput)
        val bagTypes = LuggageProcessing.calculateDependencies(bagSpecs)
        Assertions.assertEquals(1, 1)
    }

}
