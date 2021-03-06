package aoc2018.day11

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ChronalChargeTest {

    @ParameterizedTest
    @CsvSource(
            "3, 5, 8, 4",
            "122, 79, 57, -5",
            "217,196, 39, 0",
            "101,153, 71, 4"
    )
    fun calculatePowerLevel(x: Int, y: Int, serial: Int, expectedPowerLevel: Int) {
        val point = Point(x, y)
        val powerLevel = point.calculatePowerLevel(serial)
        Assertions.assertEquals(expectedPowerLevel, powerLevel)
    }

    @ParameterizedTest
    @CsvSource(
            "18, 33, 45, 29",
            "42, 21, 61, 30"
    )
    fun part1_AreaWithHighestTotalPower_Examples(serial: Int, expectedPointX: Int, expectedPointY: Int, expectedTotalPowerLevel: Int) {
        val grid = Grid(300, 300, serial)
        val pointAndPower: Pair<Point, Int> =
                grid.calculateAreaWithHighestPower(3) ?:
                throw AssertionError("Expected non null answer")
        val expectedPointAndPower = Pair(Point(expectedPointX, expectedPointY), expectedTotalPowerLevel)
        Assertions.assertEquals(expectedPointAndPower, pointAndPower)
    }

    @Test
    fun part1_AreaWithHighestTotalPower_Problem() {
        val grid = Grid(300, 300, 1788)
        val pointAndPower: Pair<Point, Int> =
                grid.calculateAreaWithHighestPower(3) ?:
                throw AssertionError("Expected non null answer")
        val expectedPointAndPower = Pair(Point(235, 35), 31)
        Assertions.assertEquals(expectedPointAndPower, pointAndPower)
    }

    @ParameterizedTest
    @CsvSource(
            "18, 90, 269, 16, 113",
            "42, 232, 251, 12, 119"
    )
    fun part2_AreaWithHighestTotalPowerAnySize_Examples(serial: Int, expectedPointX: Int, expectedPointY: Int, expectedSize: Int, expectedTotalPowerLevel: Int) {
        val grid = Grid(300, 300, serial)
        val sizeAndPointAndPower: Pair<Int, Pair<Point, Int>?>? = grid.calculateAreaOfAnySizeWithHighestPower()

        val size = sizeAndPointAndPower?.first
        Assertions.assertEquals(expectedSize, size)

        val pointAndPower = sizeAndPointAndPower?.second
        val expectedPointAndPower = Pair(Point(expectedPointX, expectedPointY), expectedTotalPowerLevel)
        Assertions.assertEquals(expectedPointAndPower, pointAndPower)
    }

    @Test
    fun part2_AreaWithHighestTotalPowerAnySize_Problem() {
        val grid = Grid(300, 300, 1788)
        val sizeAndPointAndPower: Pair<Int, Pair<Point, Int>?>? = grid.calculateAreaOfAnySizeWithHighestPower()

        val size = sizeAndPointAndPower?.first
        val point: Point? = sizeAndPointAndPower?.second?.first
        val power = sizeAndPointAndPower?.second?.second
        Assertions.assertEquals("142x2657x65", "${point?.x}x${point?.y}${size}x$power")
    }

}