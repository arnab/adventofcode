#!/bin/zsh

# Prepare for a day of AoC. Run with (for 2020 day 14):
# ./prep_a_day.sh 2020 14 AirportShuttle

YEAR=$1
DAY=$2
PUZZLE_NAME=$3
echo "Preparing for Aoc $YEAR Day $DAY: $PUZZLE_NAME"

mkdir -p src/main/kotlin/aoc"$YEAR"/day"$DAY"
cat > src/main/kotlin/aoc"$YEAR"/day"$DAY"/"$PUZZLE_NAME".kt << EOF
package aoc$YEAR.day$DAY

object $PUZZLE_NAME {
    fun parse(data: String) = data.split("\n")
}
EOF

mkdir -p src/test/kotlin/aoc"$YEAR"/day"$DAY"/
cat > src/test/kotlin/aoc"$YEAR"/day"$DAY"/"$PUZZLE_NAME"Test.kt << EOF
package aoc$YEAR.day$DAY

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class ${PUZZLE_NAME}Test {

    private val exampleInput = """
        """.trimIndent()

    private val problemInput = File("src/test/resources/aoc$YEAR/day$DAY/input.txt").readText()

    @Test
    fun \`part 1 test\`() {
        verifyPart1($PUZZLE_NAME.parse(exampleInput), 1)
    }

    @Test
    fun \`part 1 real\`() {
        verifyPart1($PUZZLE_NAME.parse(problemInput), 1)
    }

    private fun verifyPart1(data: List<String>, expectedAnswer: Int) {
        Assertions.assertEquals(1, expectedAnswer)
        TODO()
    }
}
EOF

mkdir -p src/test/resources/aoc"$YEAR"/day"$DAY"/
touch src/test/resources/aoc"$YEAR"/day"$DAY"/input.txt

echo "All done!"
