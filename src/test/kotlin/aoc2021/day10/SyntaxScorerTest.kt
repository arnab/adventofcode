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

}
