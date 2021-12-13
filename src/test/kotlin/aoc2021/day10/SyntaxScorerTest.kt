package aoc2021.day10

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class SyntaxScorerTest {

    private val exampleInput = """
          [({(<(())[]>[[{[]{<()<>>
          [(()[<>])]({[<{<<[]>>(
          {([(<{}[<>[]}>{[]{[(<()>
          (((({<>}<{<{<>}{[]{[]{}
          [[<[([]))<([[{}[[()]]]
          [{[{({}]{}}([{[{{{}}([]
          {<[[]]>}<{[{[{[]{()[[[]
          [<(<(<(<{}))><([]([]()
          <{([([[(<>()){}]>(<<{{
          <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day10/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(SyntaxScorer.parse(exampleInput), 26397)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(SyntaxScorer.parse(problemInput), 392139)
    }

    private fun verifyPart1(data: List<String>, expectedAnswer: Int) {
        Assertions.assertEquals(SyntaxScorer.calculateErrorScore(data), expectedAnswer)
    }

    @Test
    fun `part 2 test`() {
        verifyPart2(SyntaxScorer.parse(exampleInput), 288957L)
    }

    @Test
    fun `part 2 real`() {
        verifyPart2(SyntaxScorer.parse(problemInput), 4001832844L)
    }

    private fun verifyPart2(data: List<String>, expectedAnswer: Long) {
        Assertions.assertEquals(SyntaxScorer.autoCompleteAndScore(data), expectedAnswer)
    }

}
