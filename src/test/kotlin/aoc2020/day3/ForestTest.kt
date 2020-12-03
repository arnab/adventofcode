package aoc2020.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ForestTest {

    private val testInput = """
            ..##.......
            #...#...#..
            .#....#..#.
            ..#.#...#.#
            .#...##..#.
            ..#.##.....
            .#.#.#....#
            .#........#
            #.##...#...
            #...##....#
            .#..#...#.#
        """.trimIndent()

    @Test
    fun `part 1 test`() {
        val forest = Forest.parse(testInput)
        Assertions.assertEquals(7, Forest.walkAndCount(forest, stepX = 3, stepY = 1))
    }

    @Test
    fun `part 1 real`() {
        val data = File("src/test/resources/aoc2020/day3/input.txt").readText()
        val forest = Forest.parse(data)
        Assertions.assertEquals(209, Forest.walkAndCount(forest, stepX = 3, stepY = 1))
    }

    @Test
    fun `part 2 test`() {
        val forest = Forest.parse(testInput)
        Assertions.assertEquals(336, walkMultipleSlopesAndAccumulateTreeCount(forest))
    }

    @Test
    fun `part 2 real`() {
        val data = File("src/test/resources/aoc2020/day3/input.txt").readText()

        val forest = Forest.parse(data)
        Assertions.assertEquals(1574890240, walkMultipleSlopesAndAccumulateTreeCount(forest))
    }

    private fun walkMultipleSlopesAndAccumulateTreeCount(forest: List<List<Char>>) = listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2),
    ).fold(1) { acc, (stepX, stepY) ->
        acc * Forest.walkAndCount(forest, stepX, stepY)
    }
}