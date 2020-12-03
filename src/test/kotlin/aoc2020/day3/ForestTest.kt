package aoc2020.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ForestTest {

    @Test
    fun `part 1 test`() {
        val data = """
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
        val forest = Forest.parse(data)
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

    }

    @Test
    fun `part 2 real`() {

    }

}