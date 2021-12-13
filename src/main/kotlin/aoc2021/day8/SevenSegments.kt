package aoc2021.day8

object SevenSegments {
    data class Observation(
        val pattern: List<String>,
        val output: List<String>
    )

    fun parse(data: String) = data.split("\n").map { line ->
        val (pattern, output) = line.split(" | ")
        Observation(
            pattern.split(" ").map { it.toCharArray().sorted().joinToString("") },
            output.split(" ").map { it.toCharArray().sorted().joinToString("") }
        )
    }

    fun countDigitsWithUniqSegments(observations: List<Observation>) = observations.flatMap { it.output }
        .count {
            it.length == 2 || // -> 1
                it.length == 4 || // -> 4
                it.length == 3 || // -> 7
                it.length == 7 // -> 8
        }

    fun decodeAllAndSum(observations: List<Observation>) = observations.sumBy { observation ->
        val cipher = deduceCipher(observation.pattern)
        calculateValue(observation.output, cipher)
    }

    private fun deduceCipher(pattern: List<String>): Map<String, Int> {
        val cipher: MutableMap<String, Int> = mutableMapOf()
        val remainingPatternsToIdentify = pattern.toMutableList()

        val patternOne = remainingPatternsToIdentify.find { it.length == 2 }!!
        cipher[patternOne] = 1
        remainingPatternsToIdentify.remove(patternOne)

        val patternFour = remainingPatternsToIdentify.find { it.length == 4 }!!
        cipher[patternFour] = 4
        remainingPatternsToIdentify.remove(patternFour)

        val patternSeven = remainingPatternsToIdentify.find { it.length == 3 }!!
        cipher[patternSeven] = 7
        remainingPatternsToIdentify.remove(patternSeven)

        val patternEight = remainingPatternsToIdentify.find { it.length == 7 }!!
        cipher[patternEight] = 8
        remainingPatternsToIdentify.remove(patternEight)

        val patternNine = remainingPatternsToIdentify.find { it.length == 6 && it.containsPattern(patternFour) }!!
        cipher[patternNine] = 9
        remainingPatternsToIdentify.remove(patternNine)

        val patternZero = remainingPatternsToIdentify.find { it.length == 6 && it.containsPattern(patternOne) }!!
        cipher[patternZero] = 0
        remainingPatternsToIdentify.remove(patternZero)

        val patternSix = remainingPatternsToIdentify.find { it.length == 6 }!!
        cipher[patternSix] = 6
        remainingPatternsToIdentify.remove(patternSix)

        val patternThree = remainingPatternsToIdentify.find { it.length == 5 && it.containsPattern(patternOne) }!!
        cipher[patternThree] = 3
        remainingPatternsToIdentify.remove(patternThree)

        val patternFive = remainingPatternsToIdentify.find { it.length == 5 && patternSix.containsPattern(it) }!!
        cipher[patternFive] = 5
        remainingPatternsToIdentify.remove(patternFive)

        if (remainingPatternsToIdentify.size > 1) {
            throw NotImplementedError("Only one pattern (for #2) should have remained, found: $remainingPatternsToIdentify")
        }

        val patternTwo = remainingPatternsToIdentify[0]
        cipher[patternTwo] = 2
        remainingPatternsToIdentify.remove(patternTwo)

        return cipher
    }

    private fun calculateValue(output: List<String>, cipher: Map<String, Int>) =
        output.map { cipher[it] }.joinToString("").toInt()

    private fun String.containsPattern(pattern: String) = pattern.all { this.contains(it) }
}

