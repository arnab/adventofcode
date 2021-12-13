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

    private fun verifyPart1(data: Pair<Set<Foldception.Loc>, List<Foldception.Fold>>, expectedAnswer: Int, debug: Boolean = false) {
        val (locations, folds) = data
        val finalLocations = Foldception.fold(locations, listOf( folds.first()), debug)
        Foldception.printLocations(debug = true, finalLocations)
        val markedSpots = Foldception.countMarkedSpots(finalLocations)
        Assertions.assertEquals(markedSpots, expectedAnswer)
    }

    @Test
    fun `part 2 test`() {
        verifyPart2(Foldception.parse(exampleInput), 1, debug = true)
    }

    @Test
    fun `part 2 real`() {
        verifyPart2(Foldception.parse(problemInput), 1)
    }

    private fun verifyPart2(data: Pair<Set<Foldception.Loc>, List<Foldception.Fold>>, expectedAnswer: Int, debug: Boolean = false) {
        val (locations, folds) = data
        val finalLocations = Foldception.fold(locations, folds, debug)
        Foldception.printLocations(debug = true, finalLocations)
        val markedSpots = Foldception.countMarkedSpots(finalLocations)
        Assertions.assertEquals(markedSpots, expectedAnswer)
    }
}
