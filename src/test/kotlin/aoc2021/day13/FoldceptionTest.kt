package aoc2021.day13

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class FoldceptionTest {

    private val exampleInput = """
          6,10
          0,14
          9,10
          0,3
          10,4
          4,11
          6,0
          6,12
          4,1
          0,13
          10,12
          3,4
          3,0
          8,4
          1,10
          2,14
          8,10
          9,0

          fold along y=7
          fold along x=5
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2021/day13/input.txt").readText()

    @Test
    fun `part 1 test`() {
        verifyPart1(Foldception.parse(exampleInput), 17, debug = true)
    }

    @Test
    fun `part 1 real`() {
        verifyPart1(Foldception.parse(problemInput), 1)
    }

    private fun verifyPart1(data: Pair<List<Foldception.Loc>, List<Foldception.Fold>>, expectedAnswer: Int, debug: Boolean = false) {
        val (locations, folds) = data
        val newLocations = Foldception.fold(locations, folds, debug)
        val markedSpots = Foldception.countMarkedSpots(newLocations)
        Assertions.assertEquals(1, expectedAnswer)
    }
}
