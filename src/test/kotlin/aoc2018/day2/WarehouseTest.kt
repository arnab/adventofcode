package aoc2018.day2

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WarehouseTest {

    @Test
    fun calculateCheckSum_Problem1() {
        val data = TestResourceReader.readFile("aoc2018/day2/input.txt")
            .split("\n")
            .map { it.split("").filterNot { c -> c.isEmpty() } }

        val boxesWithCountByLetters = Warehouse.boxesWithCountByLetters(data)
        Assertions.assertEquals(7657, Warehouse.calculateChecksum(boxesWithCountByLetters))
    }

    @Test
    fun calculateCheckSum_Problem1_Example1() {
        val data = """
            abcdef
            bababc
            abbcde
            abcccd
            aabcdd
            abcdee
            ababab
        """.trimIndent()
            .split("\n")
            .map { it.split("").filterNot { c -> c.isEmpty() } }

        val boxesWithCountByLetters = Warehouse.boxesWithCountByLetters(data)
        Assertions.assertEquals(12, Warehouse.calculateChecksum(boxesWithCountByLetters))
    }

    @Test
    fun findPrototypeBoxes_Problem2_Example1() {
        val data = """
            abcde
            fghij
            klmno
            pqrst
            fguij
            axcye
            wvxyz""".trimIndent()
            .split("\n")
            .map { it.split("").filterNot { c -> c.isEmpty() } }

        Warehouse.reportPrototypeBoxes(data)
    }

    @Test
    fun findPrototypeBoxes_Problem2() {
        val data = TestResourceReader.readFile("aoc2018/day2/input.txt")
            .split("\n")
            .map { it.split("").filterNot { c -> c.isEmpty() } }


        Warehouse.reportPrototypeBoxes(data)
        // Reports
        // Box: [i, v, j, h, c, a, d, o, k, e, x, l, t, w, g, s, f, s, m, q, w, r, b, n, u, y] => Prototypes: [[i, v, j, h, c, a, d, o, k, e, s, l, t, w, g, s, f, s, m, q, w, r, b, n, u, y]]

        val box = "i, v, j, h, c, a, d, o, k, e, x, l, t, w, g, s, f, s, m, q, w, r, b, n, u, y".split(", ")
        val prototypeBox = "i, v, j, h, c, a, d, o, k, e, s, l, t, w, g, s, f, s, m, q, w, r, b, n, u, y".split(", ")

        Assertions.assertEquals("ivjhcadokeltwgsfmqrbnuy", box.intersect(prototypeBox).joinToString(""))
    }

}