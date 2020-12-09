package aoc2020.day8

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class InfiniteLoopTest {
    private val exampleInput = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc2020/day8/input.txt").readText()

    @Test
    fun `part 1 test`() {
        val program = InfiniteLoop.Program.parse(exampleInput)
        val state = program.eval()
        Assertions.assertEquals(5, state.currentAccumulatedValue)
    }

    @Test
    fun `part 1 real`() {
        val program = InfiniteLoop.Program.parse(problemInput)
        val state = program.eval()
        Assertions.assertEquals(1797, state.currentAccumulatedValue)
    }

    @Test
    fun `part 2 test`() {
        val program = InfiniteLoop.Program.parse(exampleInput)
        val state = program.detectAndRepair()
        Assertions.assertEquals(8, state!!.currentAccumulatedValue)
    }

    @Test
    fun `part 2 real`() {
        val program = InfiniteLoop.Program.parse(problemInput)
        val state = program.detectAndRepair()
        Assertions.assertEquals(1036, state!!.currentAccumulatedValue)
    }
}