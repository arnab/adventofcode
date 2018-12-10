package aoc2018.day8

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class LicenseOfTreeTest {

    @Test
    fun part1_SumOfMetadata_Problem1() {
        val data = parseData(TestResourceReader.readFile("aoc2018/day8/input.txt"))
        val root = LicenseOfTree.Node.of(data)
        Assertions.assertEquals(36891, root.sumOfMetadata())
    }

    @Test
    fun part1_SumOfMetadata_Example1() {
        val data = parseData("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2")
        val root = LicenseOfTree.Node.of(data)
        Assertions.assertEquals(138, root.sumOfMetadata())
    }

    @Test
    fun part2_ValueOfRoot_Problem1() {
        val data = parseData(TestResourceReader.readFile("aoc2018/day8/input.txt"))
        val root = LicenseOfTree.Node.of(data)
        Assertions.assertEquals(20083, root.value())
    }

    @Test
    fun part2_ValueOfRoot_Example1() {
        val data = parseData("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2")
        val root = LicenseOfTree.Node.of(data)
        Assertions.assertEquals(66, root.value())
    }

    private fun parseData(data: String) = data.split(" ").map { it.toInt() }.iterator()

}