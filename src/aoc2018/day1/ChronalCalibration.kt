package aoc2018.day1

import aoc.extensions.findFirstDuplicate

object ChronalCalibration {

    fun calculateFrequencies(frequencyChanges: List<Int>): List<Int> {
        return frequencyChanges.fold(mutableListOf(0)) { resultingFrequencies, change ->
            resultingFrequencies.add(resultingFrequencies.last() + change)
            resultingFrequencies
        }
    }

    fun findFirstDuplicateFrequency(frequencyChanges: List<Int>): Int? {
        return calculateFrequencies(frequencyChanges).findFirstDuplicate()
    }

}