package aoc2016.day6

import aoc.util.TestResourceReader
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SignalToNoiseTest {
    @Test fun findRealMessage_Example() {
        val data = """
                    eedadn
                    drvtee
                    eandsr
                    raavrd
                    atevrs
                    tsrnev
                    sdttsa
                    rasrtv
                    nssdts
                    ntnada
                    svetve
                    tesnvt
                    vntsnd
                    vrdear
                    dvrsen
                    enarar""".trimIndent()
        val realMessage = SignalToNoise.findRealMessage(data)
        assertEquals("easter", realMessage)
    }

    @Test fun findRealMessage_ProblemInput() {
        val data = TestResourceReader.readFile("resources/aoc2016/day6/input.txt")
        val realMessage = SignalToNoise.findRealMessage(data)
        assertEquals("xhnqpqql", realMessage)
    }
}
