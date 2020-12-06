package aoc2020.day6

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class CustomsFormsTest {

    private val exampleInput = """
            abc
            
            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day6/input.txt").readText()

    @Test
    fun `part 1 test`() {
        val groups = CustomsForms.parse(exampleInput)
        val totalAffirmativeCount = groups.sumBy { it.allAffirmativeAnswers().size }
        Assertions.assertEquals(11, totalAffirmativeCount)
    }

    @Test
    fun `part 1 real`() {
        val groups = CustomsForms.parse(problemInput)
        val totalAffirmativeCount = groups.sumBy { it.allAffirmativeAnswers().size }
        Assertions.assertEquals(7027, totalAffirmativeCount)
    }

    @Test
    fun `part 2 test`() {
        val groups = CustomsForms.parse(exampleInput)
        val totalAffirmativeCount = groups.sumBy { it.combinedAffirmativeAnswers().size }
        Assertions.assertEquals(6, totalAffirmativeCount)
    }

    @Test
    fun `part 2 real`() {
        val groups = CustomsForms.parse(problemInput)
        val totalAffirmativeCount = groups.sumBy { it.combinedAffirmativeAnswers().size }
        Assertions.assertEquals(3579, totalAffirmativeCount)
    }

}