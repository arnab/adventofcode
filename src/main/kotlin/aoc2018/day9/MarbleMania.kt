package aoc2018.day9

import java.util.*
import kotlin.math.absoluteValue

object MarbleMania {
    fun calculateHighScore(numPlayers: Int, lastMarblePoints: Int): Long {
        val scores = LongArray(numPlayers)
        val marbles = ArrayDeque<Int>().also { it.add(0) }

        (1..lastMarblePoints).forEach { marble ->
            when {
                marble % 23 == 0 -> {
                    scores[marble % numPlayers] += marble + with(marbles) {
                        shift(-7)
                        removeFirst().toLong()
                    }
                    marbles.shift(1)
                }
                else -> {
                    with(marbles) {
                        shift(1)
                        addFirst(marble)
                    }
                }
            }
        }
        return scores.max()!!
    }

    private fun <T> Deque<T>.shift(n: Int): Unit =
            when {
                n < 0 -> repeat(n.absoluteValue) {
                    addLast(removeFirst())
                }
                else -> repeat(n) {
                    addFirst(removeLast())
                }
            }

}
