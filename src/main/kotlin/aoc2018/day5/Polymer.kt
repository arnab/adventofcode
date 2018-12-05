package aoc2018.day5

data class PolymerUnit(val type: Char, val polarity: Boolean = type.isUpperCase())

object Polymer {

    fun reduce(units: List<PolymerUnit>): List<PolymerUnit> {
        return units
    }

}
