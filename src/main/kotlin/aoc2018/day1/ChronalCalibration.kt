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
        val currentFrequencyChanges: MutableList<Int> = mutableListOf()
        var duplicate: Int? = null

        while (duplicate == null) {
            currentFrequencyChanges.addAll(frequencyChanges)
            duplicate = calculateFrequencies(currentFrequencyChanges).findFirstDuplicate()
        }

        return duplicate
    }

}